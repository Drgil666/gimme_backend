package com.project.gimme.controller;


import com.project.gimme.annotation.LoginAuthorize;
import com.project.gimme.exception.ErrorCode;
import com.project.gimme.pojo.Friend;
import com.project.gimme.pojo.PersonalMsg;
import com.project.gimme.pojo.PersonalMsgUser;
import com.project.gimme.pojo.vo.CudRequestVO;
import com.project.gimme.pojo.vo.Response;
import com.project.gimme.service.*;
import com.project.gimme.utils.AssertionUtil;
import com.project.gimme.utils.PersonalMsgUtil;
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
@RequestMapping("/api/friend")
@Api(tags = "好友")
public class FriendController {
    @Resource
    private RedisService redisService;
    @Resource
    private FriendService friendService;
    @Resource
    private PersonalMsgService personalMsgService;
    @Resource
    private PersonalMsgUserService personalMsgUserService;
    @Resource
    private UserService userService;

    @ResponseBody
    @PostMapping()
    @ApiOperation(value = "创建/更新/删除好友")
    @LoginAuthorize()
    public Response<Friend> cudFriend(@ApiParam(value = "加密验证参数")
                                      @RequestHeader(TOKEN) String token,
                                      @ApiParam(value = "包含用户信息，操作信息")
                                      @RequestBody CudRequestVO<Friend, Integer> request) {
        Integer userId = redisService.getUserId(token);
        if (request.getData().getUserId() == null) {
            request.getData().setUserId(userId);
        }
        switch (request.getMethod()) {
            case CudRequestVO.CREATE_METHOD: {
                request.getData().setFriendNote(userService.getUser(request.getData().getFriendId()).getNick());
                if (friendService.getFriend(request.getData().getUserId(),
                        request.getData().getFriendId()) == null) {
                    if (friendService.createFriend(request.getData())) {
                        Friend friend = request.getData();
                        friend.setUserId(request.getData().getFriendId());
                        friend.setFriendId(userId);
                        request.getData().setFriendNote(userService.getUser(userId).getNick());
                        friendService.createFriend(friend);
                        redisService.createFriendToken(
                                request.getData().getUserId(),
                                request.getData().getFriendId());
                        PersonalMsg personalMsg = personalMsgService.createInsertFriendPersonalMsg(userId, friend.getFriendId(), "");
                        personalMsgService.createPersonalMsg(personalMsg);
                        PersonalMsgUser personalMsgUser = new PersonalMsgUser();
                        personalMsgUser.setAcceptId(friend.getFriendId());
                        personalMsgUser.setPersonalMsgId(personalMsg.getId());
                        personalMsgUserService.createPersonalMsgUser(personalMsgUser);
                        return Response.createSuc(request.getData());
                    } else {
                        return Response.createErr("添加失败!");
                    }
                } else {
                    return Response.createErr(ErrorCode.INNER_PARAM_ILLEGAL.getCode(), "好友关系已存在!");
                }
            }
            case CudRequestVO.UPDATE_METHOD: {
                if (friendService.updateFriend(request.getData()) == 1) {
                    return Response.createSuc(request.getData());
                } else {
                    return Response.createErr(ErrorCode.INNER_PARAM_ILLEGAL.getCode(),
                            "更新失败!");
                }
            }
            case CudRequestVO.DELETE_METHOD: {
                if (friendService.deleteFriend(userId, request.getKey()) > 0) {
                    for (Integer friendId : request.getKey()) {
                        redisService.deleteFriendToken(userId, friendId);
                        //处理缓存
                        PersonalMsg personalMsg = new PersonalMsg();
                        personalMsg.setOwnerId(userId);
                        personalMsg.setObjectId(friendId);
                        personalMsg.setStatus(PersonalMsgUtil.Status.TYPE_NULL.getCode());
                        personalMsg.setObjectType(PersonalMsgUtil.FriendPersonalMsg.TYPE_DELETE_FRIEND.getName());
                        personalMsg.setNote(null);
                        personalMsg.setObjectId(null);
                        personalMsgService.createPersonalMsg(personalMsg);
                        PersonalMsgUser personalMsgUser = new PersonalMsgUser();
                        personalMsgUser.setPersonalMsgId(personalMsg.getId());
                        personalMsgUser.setAcceptId(friendId);
                        personalMsgUserService.createPersonalMsgUser(personalMsgUser);
                        //通知信息
                    }
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
    @ApiOperation(value = "获取朋友关系")
    @LoginAuthorize()
    public Response<Friend> getFriend(@ApiParam(value = "加密验证参数")
                                      @RequestHeader(TOKEN) String token,
                                      @ApiParam(value = "好友id")
                                      @RequestParam(value = "friendId", required = false) Integer friendId) {
        Integer userId = redisService.getUserId(token);
        AssertionUtil.notNull(friendId, ErrorCode.BIZ_PARAM_ILLEGAL, "friendId不能为空!");
        Friend friend = friendService.getFriend(userId, friendId);
        if (friend != null) {
            return Response.createSuc(friend);
        } else {
            return Response.createErr("获取好友关系失败!");
        }
    }
}
