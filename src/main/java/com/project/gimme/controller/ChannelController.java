package com.project.gimme.controller;


import com.project.gimme.annotation.LoginAuthorize;
import com.project.gimme.exception.ErrorCode;
import com.project.gimme.pojo.Channel;
import com.project.gimme.pojo.vo.ChannelVO;
import com.project.gimme.pojo.vo.CudRequestVO;
import com.project.gimme.pojo.vo.Response;
import com.project.gimme.service.ChannelService;
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
import java.util.List;

/**
 * @author Gilbert
 * @Date 2020/9/24 16:28
 */
@Controller
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/api/channel")
@Api(tags = "频道")
public class ChannelController {
    @Resource
    private RedisService redisService;
    @Resource
    private ChannelService channelService;
    @Resource
    private ChannelUserService channelUserService;

    @ResponseBody
    @PostMapping()
    @ApiOperation(value = "创建/更新/删除频道")
    @LoginAuthorize()
    public Response<Channel> cudChannel(@ApiParam(value = "加密验证参数")
                                        @RequestHeader("Token") String token,
                                        @ApiParam(value = "包含群聊信息，操作信息")
                                        @RequestBody CudRequestVO<Channel, Integer> request) {
        Integer userId = redisService.getUserId(token);
        switch (request.getMethod()) {
            case CudRequestVO.CREATE_METHOD: {
                request.getData().setOwnerId(userId);
                if (channelService.createChannel(request.getData())) {
                    redisService.createChannelAuthorityToken(userId, request.getData().getId(),
                            UserUtil.CHANNEL_OWNER_ATTRIBUTE);
                    return Response.createSuc(request.getData());
                } else {
                    return Response.createErr(ErrorCode.BIZ_PARAM_ILLEGAL.getCode(), "创建失败!");
                }
            }
            case CudRequestVO.UPDATE_METHOD: {
                if (channelService.updateChannel(request.getData()) == 1) {
                    redisService.createGroupAuthorityToken(request.getData().getOwnerId(), request.getData().getId(),
                            UserUtil.CHANNEL_OWNER_ATTRIBUTE);
                    return Response.createSuc(request.getData());
                } else {
                    return Response.createErr(ErrorCode.BIZ_PARAM_ILLEGAL.getCode(), "更新失败!");
                }
            }
            case CudRequestVO.DELETE_METHOD: {
                channelUserService.authorityCheck(userId, request.getData().getId(), UserUtil.CHANNEL_OWNER_ATTRIBUTE);
                if (channelService.deleteChannel(request.getData().getId()) == 1) {
                    return Response.createSuc(null);
                } else {
                    return Response.createErr("删除失败!");
                }
            }
            default:
                return Response.createUnknownMethodErr();
        }
    }

    @ResponseBody
    @GetMapping()
    @ApiOperation(value = "获取频道信息")
    @LoginAuthorize()
    public Response<Channel> getChannel(@ApiParam(value = "加密验证参数")
                                        @RequestHeader("Token") String token,
                                        @ApiParam(value = "频道id")
                                        @RequestParam(value = "channelId") Integer channelId) {
        AssertionUtil.notNull(channelId, ErrorCode.BIZ_PARAM_ILLEGAL, "channelId不可为空!");
        Channel channel = channelService.getChannel(channelId);
        if (channel != null) {
            return Response.createSuc(channel);
        } else {
            return Response.createErr(ErrorCode.INNER_PARAM_ILLEGAL.getCode(), "获取失败!");
        }
    }

    @ResponseBody
    @GetMapping("/list")
    @ApiOperation(value = "获取群聊信息")
    @LoginAuthorize()
    public Response<List<Channel>> getChannelList(@ApiParam(value = "加密验证参数")
                                                  @RequestHeader("Token") String token,
                                                  @ApiParam(value = "频道id")
                                                  @RequestParam(value = "channelId") Integer channelId,
                                                  @ApiParam(value = "关键词")
                                                  @RequestParam(value = "keyword", defaultValue = "", required = false)
                                                          String keyword) {
        AssertionUtil.notNull(channelId, ErrorCode.BIZ_PARAM_ILLEGAL, "channelId不可为空!");
        List<Channel> channelList = channelService.getChannelList(keyword, channelId);
        if (channelList != null) {
            return Response.createSuc(channelList);
        } else {
            return Response.createErr(ErrorCode.INNER_PARAM_ILLEGAL.getCode(), "获取失败!");
        }
    }

    @ResponseBody
    @GetMapping("/info")
    @ApiOperation(value = "获取频道信息")
    @LoginAuthorize()
    public Response<ChannelVO> getGroupInfo(@ApiParam(value = "加密验证参数")
                                            @RequestHeader("Token") String token,
                                            @ApiParam(value = "频道id")
                                            @RequestParam(value = "channelId") Integer channelId) {
        AssertionUtil.notNull(channelId, ErrorCode.BIZ_PARAM_ILLEGAL, "groupId不可为空!");
        Integer userId = redisService.getUserId(token);
        String authority = redisService.getGroupAuthorityToken(userId, channelId);
        ChannelVO channelVO;
        if (authority != null) {
            channelVO = channelService.getChannelVoIfJoin(userId, channelId);
        } else {
            channelVO = channelService.getChannelVoIfNotJoin(channelId);
        }
        if (channelVO != null) {
            return Response.createSuc(channelVO);
        } else {
            return Response.createErr(ErrorCode.INNER_PARAM_ILLEGAL.getCode(), "获取失败!");
        }
    }
}
