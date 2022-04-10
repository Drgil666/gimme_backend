package com.project.gimme.controller;

import com.project.gimme.annotation.LoginAuthorize;
import com.project.gimme.exception.ErrorCode;
import com.project.gimme.pojo.*;
import com.project.gimme.pojo.vo.*;
import com.project.gimme.service.*;
import com.project.gimme.utils.ChatMsgUtil;
import com.project.gimme.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static com.project.gimme.utils.RedisUtil.TOKEN;

/**
 * @author Gilbert
 * @Date 2020/9/24 16:28
 */
@Controller
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/api/chatMsg")
@Api(tags = "用户")
public class ChatMsgController {
    @Resource
    private ChatMsgService chatMsgService;
    @Resource
    private RedisService redisService;
    @Resource
    private FriendService friendService;
    @Resource
    private GroupUserService groupUserService;
    @Resource
    private ChannelUserService channelUserService;
    @Resource
    private ChannelNoticeService channelNoticeService;

    @ResponseBody
    @PostMapping()
    @ApiOperation(value = "创建/更新/删除聊天信息")
    @LoginAuthorize()
    public Response<ChatMsgVO> cudChatMsg(@ApiParam(value = "加密验证参数") @RequestHeader(TOKEN) String token,
                                          @ApiParam(value = "包含用户信息，操作信息") @RequestBody CudRequestVO<ChatMsg, Integer> request) {
        Integer userId = redisService.getUserId(token);
        request.getData().setOwnerId(userId);
        chatMsgService.checkValidity(request.getData().getType(), userId, request.getData().getObjectId());
        switch (request.getMethod()) {
            case CudRequestVO.CREATE_METHOD: {
                if (chatMsgService.createChatMsg(request.getData())) {
                    if (request.getData().getType().equals(ChatMsgUtil.Character.TYPE_FRIEND.getName())) {

                    }
                    ChatMsgVO chatMsgVO = chatMsgService.getChatMsgVO(userId, request.getData().getId());
                    return Response.createSuc(chatMsgVO);
                } else {
                    return Response.createErr("发送失败!");
                }
            }
            case CudRequestVO.UPDATE_METHOD: {
                if (chatMsgService.updateChatMsg(request.getData()) == 1) {
                    ChatMsgVO chatMsgVO = chatMsgService.getChatMsgVO(userId, request.getData().getId());
                    return Response.createSuc(chatMsgVO);
                } else {
                    return Response.createErr(ErrorCode.INNER_PARAM_ILLEGAL.getCode(), "更新失败!");
                }
            }
            case CudRequestVO.DELETE_METHOD: {
                if (chatMsgService.deleteChatMsg(request.getKey()) > 0) {
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
    @PostMapping("/transmit")
    @ApiOperation(value = "创建/更新/删除聊天信息")
    @LoginAuthorize()
    public Response<String> transmitChatMsg(@ApiParam(value = "加密验证参数") @RequestHeader(TOKEN) String token,
                                            @ApiParam(value = "包含用户信息，操作信息") @RequestBody RefreshVO request) {
        Integer userId = redisService.getUserId(token);
        ChatMsg chatMsg = chatMsgService.getChatMsg(request.getChatMsgId());
        if (request.getChatType().equals(ChatMsgUtil.Character.TYPE_CHANNEL.getName())) {
            ChannelNotice channelNotice = new ChannelNotice();
            channelNotice.setCreateTime(new Date());
            channelNotice.setChannelId(request.getObjectId());
            channelNotice.setText(chatMsg.getText());
            channelNotice.setType(chatMsg.getMsgType());
            channelUserService.authorityCheck(userId, request.getObjectId(), UserUtil.ChannelCharacter.TYPE_CHANNEL_OWNER.getName());
            if (channelNoticeService.createChannelNotice(channelNotice)) {
                ChannelUser channelUser = channelUserService.getChannelUser(request.getObjectId(), userId);
                channelUser.setMsgTimestamp(new Date());
                channelUserService.updateChannelUser(channelUser);
                return Response.createSuc(null);
            } else {
                return Response.createErr("转发失败!");
            }
        } else {
            chatMsg.setType(request.getChatType());
            chatMsg.setObjectId(request.getObjectId());
            chatMsg.setOwnerId(userId);
            if (chatMsgService.createChatMsg(chatMsg)) {
                if (request.getChatType().equals(ChatMsgUtil.Character.TYPE_FRIEND.getName())) {
                    Friend friend = friendService.getFriend(userId, request.getObjectId());
                    friend.setMsgTimestamp(new Date());
                    friendService.updateFriend(friend);
                } else if (request.getChatType().equals(ChatMsgUtil.Character.TYPE_GROUP.getName())) {
                    GroupUser groupUser = groupUserService.getGroupUserByUserId(request.getObjectId(), userId);
                    groupUser.setMsgTimestamp(new Date());
                    groupUserService.updateGroupUser(groupUser);
                }
                return Response.createSuc(null);
            } else {
                return Response.createErr("转发失败!");
            }
        }
    }

    @ResponseBody
    @GetMapping()
    @ApiOperation(value = "通过id获取聊天信息")
    @LoginAuthorize()
    public Response<ChatMsg> getChatMsg(@ApiParam(value = "加密验证参数") @RequestHeader(TOKEN) String token, @ApiParam(value = "聊天信息id") @RequestParam(value = "chatMsgId") Integer chatMsgId) {
        ChatMsg chatMsg = chatMsgService.getChatMsg(chatMsgId);
        if (chatMsg != null) {
            return Response.createSuc(chatMsg);
        } else {
            return Response.createErr(ErrorCode.INNER_PARAM_ILLEGAL.getCode(), "获取失败!");
        }
    }

    @ResponseBody
    @GetMapping("/list")
    @ApiOperation(value = "通过关键词查找聊天消息列表")
    @LoginAuthorize()
    public Response<List<ChatMsg>> getChatMsgList(@ApiParam(value = "关键词") @RequestParam(value = "keyword", defaultValue = "", required = false) String keyword,
                                                  @ApiParam(value = "类型") @RequestParam(value = "type") String type,
                                                  @ApiParam(value = "对应id") @RequestParam(value = "objectId") Integer objectId) {
        List<ChatMsg> chatMsgList = chatMsgService.getChatMsgListByObjectId(type, objectId, keyword);
        if (chatMsgList != null) {
            return Response.createSuc(chatMsgList);
        } else {
            return Response.createErr("获取失败!");
        }
    }

    @ResponseBody
    @GetMapping("/listVo")
    @ApiOperation(value = "通过关键词查找聊天消息列表")
    @LoginAuthorize()
    public Response<List<ChatMsgVO>> getChatMsgVoList(@ApiParam(value = "加密验证参数")
                                                      @RequestHeader(TOKEN) String token,
                                                      @ApiParam(value = "关键词") @RequestParam(value = "keyword", defaultValue = "", required = false) String keyword,
                                                      @ApiParam(value = "类型") @RequestParam(value = "type") String type,
                                                      @ApiParam(value = "对应id") @RequestParam(value = "objectId") Integer objectId) {
        Integer userId = redisService.getUserId(token);
        List<ChatMsgVO> chatMsgVoList = null;
        if (type.equals(ChatMsgUtil.Character.TYPE_FRIEND.getName())) {
            chatMsgVoList = chatMsgService.getChatMsgVoListByFriend(userId, objectId, keyword);
        } else {
            chatMsgVoList = chatMsgService.getChatMsgVoListByObjectId(userId, type, objectId, keyword);
        }
        if (chatMsgVoList != null) {
            return Response.createSuc(chatMsgVoList);
        } else {
            return Response.createErr("获取失败!");
        }
    }

    @ResponseBody
    @GetMapping("/list/info")
    @ApiOperation(value = "获取用户好友/群聊/频道信息")
    @LoginAuthorize()
    public Response<List<MessageVO>> getMessageVoList(@ApiParam(value = "加密验证参数")
                                                      @RequestHeader(TOKEN) String token,
                                                      @ApiParam(value = "关键词")
                                                      @RequestHeader(value = "keyword", defaultValue = "") String keyword) {
        Integer userId = redisService.getUserId(token);
        List<MessageVO> messageVOList = chatMsgService.getMessageVoByUserId(userId, keyword);
        if (messageVOList != null) {
            return Response.createSuc(messageVOList);
        } else {
            return Response.createErr("信息获取失败!");
        }
    }

    @ResponseBody
    @PostMapping("/refresh")
    @ApiOperation(value = "更新信息获取时间")
    @LoginAuthorize()
    public Response<String> refresh(@ApiParam(value = "加密验证参数")
                                    @RequestHeader(TOKEN) String token,
                                    @ApiParam(value = "包含用户信息，操作信息")
                                    @RequestBody RefreshVO request) {
        Integer userId = redisService.getUserId(token);
        if (request.getChatType().equals(ChatMsgUtil.Character.TYPE_FRIEND.getName())) {
            Friend friend = friendService.getFriend(userId, request.getObjectId());
            friend.setMsgTimestamp(new Date());
            if (friendService.updateFriend(friend) == 1) {
                return Response.createSuc(null);
            } else {
                return Response.createErr("更新失败!");
            }
        } else if (request.getChatType().equals(ChatMsgUtil.Character.TYPE_GROUP.getName())) {
            GroupUser groupUser = groupUserService.getGroupUserByUserId(request.getObjectId(), userId);
            groupUser.setMsgTimestamp(new Date());
            if (groupUserService.updateGroupUser(groupUser) == 1) {
                return Response.createSuc(null);
            } else {
                return Response.createErr("更新失败!");
            }
        } else if (request.getChatType().equals(ChatMsgUtil.Character.TYPE_CHANNEL.getName())) {
            ChannelUser channelUser = channelUserService.getChannelUser(request.getObjectId(), userId);
            channelUser.setMsgTimestamp(new Date());
            if (channelUserService.updateChannelUser(channelUser) == 1) {
                return Response.createSuc(null);
            } else {
                return Response.createErr("更新失败!");
            }
        } else {
            return Response.createUnknownMethodErr();
        }
    }
}
