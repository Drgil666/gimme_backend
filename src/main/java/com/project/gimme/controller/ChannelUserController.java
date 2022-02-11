package com.project.gimme.controller;


import com.project.gimme.annotation.LoginAuthorize;
import com.project.gimme.exception.ErrorCode;
import com.project.gimme.pojo.ChannelUser;
import com.project.gimme.pojo.vo.CudRequestVO;
import com.project.gimme.pojo.vo.Response;
import com.project.gimme.service.ChannelUserService;
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

import static com.project.gimme.utils.RedisUtil.TOKEN;

/**
 * @author Gilbert
 * @Date 2020/9/24 16:28
 */
@Controller
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/api/channel/user")
@Api(tags = "频道成员")
public class ChannelUserController {
    @Resource
    private RedisService redisService;
    @Resource
    private ChannelUserService channelUserService;

    @ResponseBody
    @PostMapping()
    @ApiOperation(value = "创建/更新/删除频道关系")
    @LoginAuthorize()
    public Response<ChannelUser> cudChannelUser(@ApiParam(value = "加密验证参数")
                                                @RequestHeader(TOKEN) String token,
                                                @ApiParam(value = "包含群聊信息，操作信息")
                                                @RequestBody CudRequestVO<ChannelUser, Integer> request) {
        Integer userId = redisService.getUserId(token);
        channelUserService.authorityCheck(userId, request.getData().getChannelId(),
                UserUtil.GROUP_ADMIN_ATTRIBUTE);
        switch (request.getMethod()) {
            case CudRequestVO.CREATE_METHOD: {
                boolean isExist = false;
                if (redisService.getChannelAuthorityToken(request.getData().getUserId(),
                        request.getData().getChannelId()) != null) {
                    //token内已存在
                    isExist = true;
                } else {
                    if (channelUserService.getChannelUser(request.getData().getChannelId(), request.getData().getUserId()) != null) {
                        isExist = true;
                        ChannelUser channelUser = channelUserService.getChannelUser(request.getData().getChannelId(),
                                request.getData().getUserId());
                        redisService.createGroupAuthorityToken(channelUser.getUserId(),
                                channelUser.getChannelId(),
                                UserUtil.CHANNEL_CHARACTER_LIST[channelUser.getType()].getName());
                        //新建token
                    }
                }
                if (isExist) {
                    return Response.createErr("该成员已在群聊中!");
                } else {
                    if (channelUserService.createChannelUser(request.getData())) {
                        redisService.createChannelAuthorityToken(request.getData().getUserId(),
                                request.getData().getChannelId(),
                                UserUtil.CHANNEL_CHARACTER_LIST[request.getData().getType()].getName());
                        return Response.createSuc(request.getData());
                    } else {
                        return Response.createErr(ErrorCode.BIZ_PARAM_ILLEGAL.getCode(), "加入群聊失败!");
                    }
                }
            }
            case CudRequestVO.UPDATE_METHOD: {
                if (channelUserService.updateChannelUser(request.getData()) == 1) {
                    redisService.createChannelAuthorityToken(request.getData().getUserId(),
                            request.getData().getChannelId(),
                            UserUtil.CHANNEL_CHARACTER_LIST[request.getData().getType()].getName());
                    return Response.createSuc(request.getData());
                } else {
                    return Response.createErr(ErrorCode.BIZ_PARAM_ILLEGAL.getCode(), "更新失败!");
                }
            }
            case CudRequestVO.DELETE_METHOD: {
                if (channelUserService.deleteChannelUser(request.getData().getChannelId(), request.getKey()) > 0) {
                    for (Integer memberId : request.getKey()) {
                        redisService.deleteGroupAuthorityToken(memberId, request.getData().getChannelId());
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
    public Response<ChannelUser> getChannelUser(@ApiParam(value = "加密验证参数")
                                                @RequestHeader(TOKEN) String token,
                                                @ApiParam(value = "频道id")
                                                @RequestParam(value = "channelId") Integer channelId) {
        Integer userId = redisService.getUserId(token);
        AssertionUtil.notNull(channelId, ErrorCode.BIZ_PARAM_ILLEGAL, "groupId不可为空!");
        channelUserService.authorityCheck(userId, channelId, UserUtil.GROUP_USER_ATTRIBUTE);
        ChannelUser channelUser = channelUserService.getChannelUser(channelId, userId);
        if (channelUser != null) {
            return Response.createSuc(channelUser);
        } else {
            return Response.createErr(ErrorCode.BIZ_PARAM_ILLEGAL.getCode(), "获取失败!");
        }
    }
}
