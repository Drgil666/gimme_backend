package com.project.gimme.controller;


import com.project.gimme.annotation.LoginAuthorize;
import com.project.gimme.exception.ErrorCode;
import com.project.gimme.pojo.Friend;
import com.project.gimme.pojo.User;
import com.project.gimme.pojo.vo.CudRequestVO;
import com.project.gimme.pojo.vo.LoginVO;
import com.project.gimme.pojo.vo.Response;
import com.project.gimme.pojo.vo.UserVO;
import com.project.gimme.service.FriendService;
import com.project.gimme.service.RedisService;
import com.project.gimme.service.UserService;
import com.project.gimme.utils.ChatMsgUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Gilbert
 * @Date 2020/9/24 16:28
 */
@Controller
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/api/user")
@Api(tags = "用户")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private RedisService redisService;
    @Resource
    private FriendService friendService;

    @ResponseBody
    @PostMapping()
    @ApiOperation(value = "创建/更新/删除用户")
    @LoginAuthorize()
    public Response<User> cudUser(@ApiParam(value = "包含用户信息，操作信息")
                                  @RequestBody CudRequestVO<User, Integer> request) {
        switch (request.getMethod()) {
            case CudRequestVO.CREATE_METHOD: {
                userService.createUser(request.getData());
                if ((request.getData().getId()) != null) {
                    return Response.createSuc(request.getData());
                } else {
                    return Response.createErr("创建用户失败!");
                }
            }
            case CudRequestVO.UPDATE_METHOD: {
                if (userService.updateUser(request.getData()) == 1) {
                    return Response.createSuc(request.getData());
                } else {
                    return Response.createErr(ErrorCode.INNER_PARAM_ILLEGAL.getCode(), "更新用户失败!");
                }
            }
            default:
                return Response.createUnknownMethodErr();
        }
    }

    @ResponseBody
    @GetMapping()
    @ApiOperation(value = "通过用户id获取用户")
    @LoginAuthorize()
    public Response<User> getUser(@ApiParam(value = "加密验证参数")
                                  @RequestHeader("Token") String token,
                                  @ApiParam(value = "用户id")
                                  @RequestParam(value = "userId", required = false) Integer userId) {
        if (userId == null) {
            userId = redisService.getUserId(token);
        }
        User user = userService.getUser(userId);
        if (user != null) {
            return Response.createSuc(user);
        } else {
            return Response.createErr(ErrorCode.INNER_PARAM_ILLEGAL.getCode(), "用户不存在!");
        }
    }

    @ResponseBody
    @PostMapping("/login")
    @ApiOperation(value = "登录")
    public Response<String> login(@ApiParam(value = "包含用户信息，操作信息")
                                  @RequestBody LoginVO loginVO) {
        Integer userId = loginVO.getUserId();
        String password = loginVO.getPassword();
        String uuid = userService.login(userId, password);
        if (uuid != null) {
            return Response.createSuc(uuid);
        } else {
            return Response.createErr("用户名或密码错误!");
        }
    }

    @ResponseBody
    @GetMapping("/list")
    @ApiOperation(value = "通过关键词查找用户列表")
    @LoginAuthorize()
    public Response<List<User>> getUserByIdAndNick(@ApiParam(value = "关键词")
                                                   @RequestParam(value = "keyword") String keyword) {
        if (StringUtils.isEmpty(keyword)) {
            return Response.createSuc(new ArrayList<>());
        }
        List<User> userList = userService.getUserByIdAndNick(keyword);
        return Response.createSuc(userList);
    }

    @ResponseBody
    @GetMapping("/{friendId}")
    @ApiOperation(value = "通过关键词查找用户列表")
    @LoginAuthorize()
    public Response<UserVO> user(@ApiParam(value = "加密验证参数")
                                 @RequestHeader("Token") String token,
                                 @ApiParam(value = "好友id")
                                 @PathVariable(value = "friendId") Integer friendId,
                                 @ApiParam(value = "获取方式")
                                 @RequestParam(value = "type") String type,
                                 @ApiParam(value = "群聊/频道id")
                                 @RequestParam(value = "objectId", required = false) Integer objectId) {
        Integer userId = redisService.getUserId(token);
        boolean isFriend = false;
        if (redisService.checkFriendToken(userId, friendId)) {
            //redis缓存有好友关系
            isFriend = true;
        } else {
            Friend friend1 = friendService.getFriend(userId, friendId);
            Friend friend2 = friendService.getFriend(friendId, userId);
            if (friend1 != null && friend2 != null) {
                isFriend = true;
                redisService.createFriendToken(userId, friendId);
            }
        }
        UserVO userVO;
        if (type.equals(ChatMsgUtil.Character.TYPE_FRIEND.getName())) {
            if (isFriend) {
                userVO = userService.getUserVoByFriendIfFriend(friendId, userId);
            } else {
                userVO = userService.getUserVoByFriendIfNotFriend(friendId);
            }
        } else if (type.equals(ChatMsgUtil.Character.TYPE_GROUP.getName())) {
            if (isFriend) {
                userVO = userService.getUserVoByGroupIfFriend(objectId, friendId, userId);
            } else {
                userVO = userService.getUserVoByGroupIfNotFriend(objectId, friendId);
            }
        } else if (type.equals(ChatMsgUtil.Character.TYPE_CHANNEL.getName())) {
            if (isFriend) {
                userVO = userService.getUserVoByChannelIfFriend(objectId, friendId, userId);
            } else {
                userVO = userService.getUserVoByChannelIfNotFriend(objectId, friendId);
            }
        } else {
            return Response.createUnknownTypeErr();
        }
        if (userVO != null) {
            return Response.createSuc(userVO);
        } else {
            return Response.createErr(
                    ErrorCode.BIZ_PARAM_ILLEGAL.getCode(),
                    "获取用户信息失败!");
        }
    }

    @ResponseBody
    @GetMapping("/friendList")
    @ApiOperation(value = "通过关键词查找用户列表")
    @LoginAuthorize()
    public Response<List<User>> getFriendList(@ApiParam(value = "加密验证参数")
                                              @RequestHeader("Token") String token,
                                              @ApiParam(value = "关键词")
                                              @RequestParam(value = "keyword", defaultValue = "")
                                                      String keyword) {
        Integer userId = redisService.getUserId(token);
        List<User> userList = userService.getFriendUserList(userId, keyword);
        return Response.createSuc(userList);
    }

    @ResponseBody
    @GetMapping("/check")
    public String check() {
        return "ok2";
    }
}
