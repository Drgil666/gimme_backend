package com.project.gimme.controller;


import com.project.gimme.annotation.LoginAuthorize;
import com.project.gimme.exception.ErrorCode;
import com.project.gimme.pojo.GroupNotice;
import com.project.gimme.pojo.vo.CudRequestVO;
import com.project.gimme.pojo.vo.Response;
import com.project.gimme.service.GroupNoticeService;
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

import static com.project.gimme.utils.RedisUtil.TOKEN;

/**
 * @author Gilbert
 * @Date 2020/9/24 16:28
 */
@Controller
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/api/group/notice")
@Api(tags = "频道公告")
public class GroupNoticeController {
    @Resource
    private GroupNoticeService groupNoticeService;
    @Resource
    private GroupUserService groupUserService;
    @Resource
    private RedisService redisService;

    @ResponseBody
    @PostMapping()
    @ApiOperation(value = "创建/更新/删除群聊公告")
    @LoginAuthorize()
    public Response<GroupNotice> cudGroupNotice(@ApiParam(value = "加密验证参数")
                                                @RequestHeader(TOKEN) String token,
                                                @ApiParam(value = "包含用户信息，操作信息")
                                                @RequestBody CudRequestVO<GroupNotice, Integer> request) {
        Integer userId = redisService.getUserId(token);
        groupUserService.authorityCheck(userId, request.getData().getGroupId(),
                UserUtil.GROUP_ADMIN_ATTRIBUTE);
        switch (request.getMethod()) {
            case CudRequestVO.CREATE_METHOD: {
                if (groupNoticeService.createGroupNotice(request.getData()) != null) {
                    return Response.createSuc(request.getData());
                } else {
                    return Response.createErr("创建公告失败!");
                }
            }
            case CudRequestVO.UPDATE_METHOD: {
                if (groupNoticeService.updateGroupNotice(request.getData()) == 1) {
                    return Response.createSuc(request.getData());
                } else {
                    return Response.createErr(ErrorCode.INNER_PARAM_ILLEGAL.getCode(), "更新用户失败!");
                }
            }
            case CudRequestVO.DELETE_METHOD: {
                if (groupNoticeService.deleteGroupNotice(request.getData().getGroupId(),
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
    public Response<GroupNotice> getGroupNotice(@ApiParam(value = "加密验证参数")
                                                @RequestHeader(TOKEN) String token,
                                                @ApiParam(value = "频道id")
                                                @RequestParam(value = "groupId")
                                                        Integer groupId,
                                                @ApiParam(value = "公告id")
                                                @RequestParam(value = "groupNoticeId") Integer groupNoticeId) {
        Integer userId = redisService.getUserId(token);
        groupUserService.authorityCheck(userId, groupId, UserUtil.CHANNEL_USER_ATTRIBUTE);
        GroupNotice groupNotice = groupNoticeService.getGroupNotice(groupNoticeId);
        if (groupNotice != null) {
            return Response.createSuc(groupNotice);
        } else {
            return Response.createErr(ErrorCode.INNER_PARAM_ILLEGAL.getCode(), "公告不存在!");
        }
    }

    @ResponseBody
    @GetMapping("/list")
    @ApiOperation(value = "获取群聊公告列表")
    @LoginAuthorize()
    public Response<List<GroupNotice>> getGroupNoticeList(@ApiParam(value = "加密验证参数")
                                                          @RequestHeader(TOKEN) String token,
                                                          @ApiParam(value = "群id")
                                                          @RequestParam(value = "groupId") Integer groupId) {
        Integer userId = redisService.getUserId(token);
        AssertionUtil.notNull(groupId, ErrorCode.BIZ_PARAM_ILLEGAL, "groupId不能为空!");
        groupUserService.authorityCheck(userId, groupId, UserUtil.GROUP_USER_ATTRIBUTE);
        List<GroupNotice> groupNoticeList = groupNoticeService.getGroupNoticeList(groupId);
        if (groupNoticeList != null) {
            return Response.createSuc(groupNoticeList);
        } else {
            return Response.createErr(ErrorCode.BIZ_PARAM_ILLEGAL.getCode(), "获取公告失败!");
        }
    }
}
