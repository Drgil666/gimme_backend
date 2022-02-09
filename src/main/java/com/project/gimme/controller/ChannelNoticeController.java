package com.project.gimme.controller;


import com.project.gimme.annotation.LoginAuthorize;
import com.project.gimme.exception.ErrorCode;
import com.project.gimme.pojo.ChannelNotice;
import com.project.gimme.pojo.vo.CudRequestVO;
import com.project.gimme.pojo.vo.Response;
import com.project.gimme.service.ChannelNoticeService;
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

import static com.project.gimme.utils.RedisUtil.TOKEN;

/**
 * @author Gilbert
 * @Date 2020/9/24 16:28
 */
@Controller
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/api/channel/notice")
@Api(tags = "频道公告")
public class ChannelNoticeController {
    @Resource
    private ChannelNoticeService channelNoticeService;
    @Resource
    private ChannelUserService channelUserService;
    @Resource
    private RedisService redisService;

    @ResponseBody
    @PostMapping()
    @ApiOperation(value = "创建/更新/删除频道公告")
    @LoginAuthorize()
    public Response<ChannelNotice> cudChannelNotice(@ApiParam(value = "加密验证参数")
                                                    @RequestHeader(TOKEN) String token,
                                                    @ApiParam(value = "包含用户信息，操作信息")
                                                    @RequestBody CudRequestVO<ChannelNotice, Integer> request) {
        Integer userId = redisService.getUserId(token);
        channelUserService.authorityCheck(userId, request.getData().getChannelId(), UserUtil.CHANNEL_OWNER_ATTRIBUTE);
        switch (request.getMethod()) {
            case CudRequestVO.CREATE_METHOD: {
                if (channelNoticeService.createChannelNotice(request.getData()) != null) {
                    return Response.createSuc(request.getData());
                } else {
                    return Response.createErr("创建用户失败!");
                }
            }
            case CudRequestVO.UPDATE_METHOD: {
                if (channelNoticeService.updateChannelNotice(request.getData()) == 1) {
                    return Response.createSuc(request.getData());
                } else {
                    return Response.createErr(ErrorCode.INNER_PARAM_ILLEGAL.getCode(), "更新用户失败!");
                }
            }
            case CudRequestVO.DELETE_METHOD: {
                if (channelNoticeService.deleteChannelNotice(request.getData().getChannelId(),
                        request.getKey()) > 0) {
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
    @ApiOperation(value = "通过频道公告id获取频道")
    @LoginAuthorize()
    public Response<ChannelNotice> getChannelNotice(@ApiParam(value = "加密验证参数")
                                                        @RequestHeader(TOKEN) String token,
                                                    @ApiParam(value = "频道id")
                                                        @RequestParam(value = "channelId")
                                                                Integer channelId,
                                                    @ApiParam(value = "用户id")
                                                        @RequestParam(value = "channelNoticeId") Integer channelNoticeId) {
        Integer userId = redisService.getUserId(token);
        channelUserService.authorityCheck(userId, channelId, UserUtil.CHANNEL_USER_ATTRIBUTE);
        ChannelNotice channelNotice = channelNoticeService.getChannelNotice(channelNoticeId);
        if (channelNotice != null) {
            return Response.createSuc(channelNotice);
        } else {
            return Response.createErr(ErrorCode.INNER_PARAM_ILLEGAL.getCode(), "频道公告不存在!");
        }
    }

    @ResponseBody
    @GetMapping("/list")
    @ApiOperation(value = "获取频道公告列表")
    @LoginAuthorize()
    public Response<List<ChannelNotice>> getChannelNoticeList(@ApiParam(value = "加密验证参数")
                                                                  @RequestHeader(TOKEN) String token,
                                                              @ApiParam(value = "频道id")
                                                                  @RequestParam(value = "channelId") Integer channelId) {
        Integer userId = redisService.getUserId(token);
        channelUserService.authorityCheck(userId, channelId, UserUtil.CHANNEL_USER_ATTRIBUTE);
        AssertionUtil.notNull(channelId, ErrorCode.BIZ_PARAM_ILLEGAL, "channelId不能为空!");
        List<ChannelNotice> channelNoticeList = channelNoticeService.getChannelNoticeList(channelId);
        if (channelNoticeList != null) {
            return Response.createSuc(channelNoticeList);
        } else {
            return Response.createErr(ErrorCode.BIZ_PARAM_ILLEGAL.getCode(), "获取频道公告失败!");
        }
    }
}
