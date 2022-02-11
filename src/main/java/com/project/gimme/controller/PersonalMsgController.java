package com.project.gimme.controller;


import com.project.gimme.annotation.LoginAuthorize;
import com.project.gimme.exception.ErrorCode;
import com.project.gimme.pojo.PersonalMsg;
import com.project.gimme.pojo.PersonalMsgUser;
import com.project.gimme.pojo.vo.CudRequestVO;
import com.project.gimme.pojo.vo.Response;
import com.project.gimme.service.PersonalMsgService;
import com.project.gimme.service.PersonalMsgUserService;
import com.project.gimme.service.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static com.project.gimme.utils.RedisUtil.TOKEN;
import static com.project.gimme.utils.UserUtil.ADMIN_ATTRIBUTE;

/**
 * @author Gilbert
 * @Date 2020/9/24 16:28
 */
@Controller
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/api/personalMsg")
@Api(tags = "用户")
public class PersonalMsgController {
    @Resource
    private PersonalMsgService personalMsgService;
    @Resource
    private PersonalMsgUserService personalMsgUserService;
    @Resource
    private RedisService redisService;

    @ResponseBody
    @PostMapping()
    @ApiOperation(value = "创建/更新/删除个人消息")
    @LoginAuthorize()
    public Response<PersonalMsg> cudPersonalMsg(@ApiParam(value = "加密验证参数")
                                                @RequestHeader(TOKEN) String token, @ApiParam(value = "包含用户信息，操作信息")
                                                @RequestBody CudRequestVO<PersonalMsg, Integer> request) {
        Integer userId = redisService.getUserId(token);
        switch (request.getMethod()) {
            case CudRequestVO.CREATE_METHOD: {
                personalMsgService.checkValidity(request.getData().getType(),
                        userId, request.getData().getObjectId(), ADMIN_ATTRIBUTE);
                if (personalMsgService.createPersonalMsg(request.getData())) {
                    return Response.createSuc(request.getData());
                    //双方都会收到信息
                } else {
                    return Response.createErr("操作失败!");
                }
            }
            default:
                return Response.createUnknownMethodErr();
        }
    }

    @ResponseBody
    @GetMapping()
    @ApiOperation(value = "通过id获取个人消息")
    @LoginAuthorize()
    public Response<PersonalMsg> getPersonalMsg(@ApiParam(value = "加密验证参数")
                                                @RequestHeader(TOKEN) String token,
                                                @ApiParam(value = "个人信息id")
                                                @RequestParam(value = "personalMsgId") Integer personalMsgId) {
        Integer userId = redisService.getUserId(token);
        PersonalMsgUser personalMsgUser = personalMsgUserService.getPersonalMsgUser(personalMsgId, userId);
        if (personalMsgUser != null) {
            PersonalMsg personalMsg = personalMsgService.getPersonalMsg(personalMsgId);
            if (personalMsg != null) {
                return Response.createSuc(personalMsg);
            }
        }
        return Response.createErr(ErrorCode.INNER_PARAM_ILLEGAL.getCode(), "获取失败!");
    }

    @ResponseBody
    @GetMapping("/list")
    @ApiOperation(value = "通过关键词查找用户列表")
    @LoginAuthorize()
    public Response<List<PersonalMsg>> getPersonalMsgList(@ApiParam(value = "加密验证参数")
                                                          @RequestHeader(TOKEN) String token) {
        Integer userId = redisService.getUserId(token);
        List<PersonalMsg> personalMsgList = personalMsgService.getPersonalMsgList(userId);
        if (personalMsgList != null) {
            return Response.createSuc(personalMsgList);
        } else {
            return Response.createErr("获取失败!");
        }
    }
}
