package com.project.gimme.controller;


import com.project.gimme.annotation.LoginAuthorize;
import com.project.gimme.exception.ErrorCode;
import com.project.gimme.pojo.GroupUser;
import com.project.gimme.pojo.vo.CudRequestVO;
import com.project.gimme.pojo.vo.Response;
import com.project.gimme.service.GroupUserService;
import com.project.gimme.service.RedisService;
import com.project.gimme.service.UserService;
import com.project.gimme.utils.AssertionUtil;
import com.project.gimme.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;

import static com.project.gimme.utils.RedisUtil.TOKEN;

/**
 * @author Gilbert
 * @Date 2020/9/24 16:28
 */
@Controller
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/api/group/user")
@Api(tags = "群聊成员")
public class GroupUserController {
    @Resource
    private RedisService redisService;
    @Resource
    private GroupUserService groupUserService;
    @Resource
    private UserService userService;

    @ResponseBody
    @PostMapping()
    @ApiOperation(value = "创建/更新/删除群聊关系")
    @LoginAuthorize()
    public Response<GroupUser> cudGroupUser(@ApiParam(value = "加密验证参数")
                                            @RequestHeader(TOKEN) String token,
                                            @ApiParam(value = "包含群聊信息，操作信息")
                                            @RequestBody CudRequestVO<GroupUser, Integer> request) {
        Integer userId = redisService.getUserId(token);
        if (request.getData().getUserId() == null) {
            request.getData().setUserId(userId);
        }
        switch (request.getMethod()) {
            case CudRequestVO.CREATE_METHOD: {
//                groupUserService.authorityCheck(userId, request.getData().getGroupId(), UserUtil.GROUP_USER_ATTRIBUTE);
                boolean isExist = false;
                if (redisService.getGroupAuthorityToken(request.getData().getUserId(),
                        request.getData().getGroupId()) != null) {
                    //token内已存在
                    isExist = true;
                } else {
                    if (groupUserService.getGroupUserByUserId(request.getData().getGroupId(), request.getData().getUserId()) != null) {
                        isExist = true;
                        GroupUser groupUser = groupUserService.getGroupUserByUserId(request.getData().getGroupId(), request.getData().getUserId());
                        redisService.createGroupAuthorityToken(groupUser.getUserId(),
                                groupUser.getGroupId(), groupUser.getType());
                        //新建token
                    }
                }
                if (isExist) {
                    return Response.createErr("该成员已在群聊中!");
                } else {
                    request.getData().setGroupNick(userService.getUser(request.getData().getUserId()).getNick());
                    if (groupUserService.createGroupUser(request.getData())) {
                        redisService.createGroupAuthorityToken(request.getData().getUserId(),
                                request.getData().getGroupId(), request.getData().getType());
                        return Response.createSuc(request.getData());
                    } else {
                        return Response.createErr(ErrorCode.BIZ_PARAM_ILLEGAL.getCode(), "加入群聊失败!");
                    }
                }
            }
            case CudRequestVO.UPDATE_METHOD: {
                groupUserService.authorityCheck(userId, request.getData().getGroupId(), UserUtil.GROUP_USER_ATTRIBUTE);
                if (groupUserService.updateGroupUser(request.getData()) == 1) {
                    redisService.createGroupAuthorityToken(request.getData().getUserId(),
                            request.getData().getGroupId(), request.getData().getType());
                    return Response.createSuc(request.getData());
                } else {
                    return Response.createErr(ErrorCode.BIZ_PARAM_ILLEGAL.getCode(), "更新失败!");
                }
            }
            case CudRequestVO.DELETE_METHOD: {
                groupUserService.authorityCheck(userId, request.getData().getGroupId(), UserUtil.GROUP_USER_ATTRIBUTE);
                if (request.getKey().isEmpty()) {
                    request.setKey(new ArrayList<>());
                    request.getKey().add(userId);
                }
                if (groupUserService.deleteGroupUser(request.getData().getGroupId(), request.getKey()) > 0) {
                    for (Integer memberId : request.getKey()) {
                        redisService.deleteGroupAuthorityToken(memberId, request.getData().getGroupId());
                    }
                    //批量删除token
                    return Response.createSuc(null);
                } else {
                    return Response.createErr(ErrorCode.BIZ_PARAM_ILLEGAL.getCode(), "删除失败!");
                }
            }
            default:
                return Response.createUnknownMethodErr();
        }
    }

    @ResponseBody
    @GetMapping()
    @ApiOperation(value = "获取群聊信息")
    @LoginAuthorize()
    public Response<GroupUser> getGroupUser(@ApiParam(value = "加密验证参数")
                                            @RequestHeader(TOKEN) String token,
                                            @ApiParam(value = "群聊id")
                                            @RequestParam(value = "groupId") Integer groupId) {
        Integer userId = redisService.getUserId(token);
        AssertionUtil.notNull(groupId, ErrorCode.BIZ_PARAM_ILLEGAL, "groupId不可为空!");
        groupUserService.authorityCheck(userId, groupId, UserUtil.GROUP_USER_ATTRIBUTE);
        GroupUser groupUser = groupUserService.getGroupUserByUserId(groupId, userId);
        if (groupUser != null) {
            return Response.createSuc(groupUser);
        } else {
            return Response.createErr(ErrorCode.BIZ_PARAM_ILLEGAL.getCode(), "获取失败!");
        }
    }
}
