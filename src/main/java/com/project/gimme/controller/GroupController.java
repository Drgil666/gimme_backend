package com.project.gimme.controller;


import com.project.gimme.annotation.LoginAuthorize;
import com.project.gimme.exception.ErrorCode;
import com.project.gimme.pojo.Group;
import com.project.gimme.pojo.GroupUser;
import com.project.gimme.pojo.vo.CudRequestVO;
import com.project.gimme.pojo.vo.GroupVO;
import com.project.gimme.pojo.vo.Response;
import com.project.gimme.service.GroupService;
import com.project.gimme.service.GroupUserService;
import com.project.gimme.service.RedisService;
import com.project.gimme.utils.AssertionUtil;
import com.project.gimme.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Gilbert
 * @Date 2020/9/24 16:28
 */
@Controller
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/api/group")
@Api(tags = "群聊")
public class GroupController {
    @Resource
    private RedisService redisService;
    @Resource
    private GroupService groupService;
    @Resource
    private GroupUserService groupUserService;

    @ResponseBody
    @PostMapping()
    @ApiOperation(value = "创建/更新/删除群聊")
    @LoginAuthorize()
    public Response<Group> cudGroup(@ApiParam(value = "加密验证参数")
                                    @RequestHeader("Token") String token,
                                    @ApiParam(value = "包含群聊信息，操作信息")
                                    @RequestBody CudRequestVO<Group, Integer> request) {
        Integer userId = redisService.getUserId(token);
        switch (request.getMethod()) {
            case CudRequestVO.CREATE_METHOD: {
                if (groupService.createGroup(request.getData())) {
                    GroupUser groupUser = new GroupUser();
                    groupUser.setGroupId(request.getData().getId());
                    groupUser.setUserId(userId);
                    groupUser.setType(UserUtil.GroupCharacter.TYPE_GROUP_OWNER.getCode());
                    groupUserService.createGroupUser(groupUser);
                    //自己成为群主
                    return Response.createSuc(request.getData());
                } else {
                    return Response.createErr(ErrorCode.UNKNOWN_ERROR.getCode(), "创建群聊失败!");
                }
            }
            case CudRequestVO.UPDATE_METHOD: {
                groupUserService.authorityCheck(userId, request.getData().getId(),
                        UserUtil.GROUP_ADMIN_ATTRIBUTE);
                if (groupService.updateGroup(request.getData()) == 1) {
                    return Response.createSuc(request.getData());
                } else {
                    return Response.createErr(ErrorCode.BIZ_PARAM_ILLEGAL.getCode(), "更新群聊失败!");
                }
            }
            case CudRequestVO.DELETE_METHOD: {
                if (groupService.deleteGroup(request.getKey()) > 0) {
                    for (Integer groupId : request.getKey()) {
                        redisService.deleteGroupToken(groupId);
                    }
                    //批量删除Token
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
    public Response<Group> getGroup(@ApiParam(value = "加密验证参数")
                                    @RequestHeader("Token") String token,
                                    @ApiParam(value = "群聊id")
                                    @RequestParam(value = "groupId") Integer groupId) {
        AssertionUtil.notNull(groupId, ErrorCode.BIZ_PARAM_ILLEGAL, "groupId不可为空!");
        Group group = groupService.getGroup(groupId);
        if (group != null) {
            return Response.createSuc(group);
        } else {
            return Response.createErr(ErrorCode.INNER_PARAM_ILLEGAL.getCode(), "获取失败!");
        }
    }

    @ResponseBody
    @GetMapping("/list")
    @ApiOperation(value = "获取群聊信息")
    @LoginAuthorize()
    public Response<List<Group>> getGroupList(@ApiParam(value = "加密验证参数")
                                              @RequestHeader("Token") String token,
                                              @ApiParam(value = "群聊id")
                                              @RequestParam(value = "groupId") Integer groupId) {
        AssertionUtil.notNull(groupId, ErrorCode.BIZ_PARAM_ILLEGAL, "groupId不可为空!");
        List<Group> groupList = groupService.getGroupList(groupId);
        if (groupList != null) {
            return Response.createSuc(groupList);
        } else {
            return Response.createErr(ErrorCode.INNER_PARAM_ILLEGAL.getCode(), "获取失败!");
        }
    }

    @ResponseBody
    @GetMapping("/info")
    @ApiOperation(value = "获取群聊信息")
    @LoginAuthorize()
    public Response<GroupVO> getGroupInfo(@ApiParam(value = "加密验证参数")
                                          @RequestHeader("Token") String token,
                                          @ApiParam(value = "群聊id")
                                          @RequestParam(value = "groupId") Integer groupId) {
        AssertionUtil.notNull(groupId, ErrorCode.BIZ_PARAM_ILLEGAL, "groupId不可为空!");
        Integer userId = redisService.getUserId(token);
        String authority = redisService.getGroupAuthorityToken(userId, groupId);
        GroupVO groupVO;
        if (authority != null) {
            groupVO = groupService.getGroupVoIfJoin(userId, groupId);
        } else {
            groupVO = groupService.getGroupVoIfNotJoin(groupId);
        }
        if (groupVO != null) {
            return Response.createSuc(groupVO);
        } else {
            return Response.createErr(ErrorCode.INNER_PARAM_ILLEGAL.getCode(), "获取失败!");
        }
    }
}