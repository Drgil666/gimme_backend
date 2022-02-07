package com.project.gimme.controller;


import com.project.gimme.annotation.LoginAuthorize;
import com.project.gimme.exception.ErrorCode;
import com.project.gimme.pojo.User;
import com.project.gimme.pojo.vo.CudRequestVO;
import com.project.gimme.pojo.vo.LoginVO;
import com.project.gimme.pojo.vo.Response;
import com.project.gimme.service.RedisService;
import com.project.gimme.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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

    @ResponseBody
    @PostMapping()
    @ApiOperation(value = "创建/更新/删除用户")
    @LoginAuthorize()
    public Response<User> user(@ApiParam(value = "包含用户信息，操作信息")
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
    public Response<User> user(@ApiParam(value = "加密验证参数")
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
    public Response<String> user(@ApiParam(value = "包含用户信息，操作信息")
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
    @GetMapping("/check")
    public String check() {
        return "ok2";
    }
}
