package com.project.gimme.controller;

import com.project.gimme.annotation.LoginAuthorize;
import com.project.gimme.exception.ErrorCode;
import com.project.gimme.pojo.vo.Response;
import com.project.gimme.pojo.vo.SearchVO;
import com.project.gimme.service.*;
import com.project.gimme.utils.AssertionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.project.gimme.utils.RedisUtil.TOKEN;

/**
 * @author Gilbert
 * @date 2022/3/23 12:27
 */
@Controller
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/api/search")
@Api(tags = "搜索")
public class SearchController {
    @Resource
    private FriendService friendService;
    @Resource
    private GroupService groupService;
    @Resource
    private ChannelService channelService;
    @Resource
    private UserService userService;
    @Resource
    private RedisService redisService;

    @ResponseBody
    @GetMapping()
    @ApiOperation(value = "通过id获取个人消息")
    @LoginAuthorize()
    public Response<List<SearchVO>> getSearchVoList(@ApiParam(value = "加密验证参数")
                                                        @RequestHeader(TOKEN) String token,
                                                    @ApiParam(value = "个人信息id")
                                                        @RequestParam(value = "keyword", defaultValue = "") String keyword,
                                                    @ApiParam(value = "查询类型")
                                                        @RequestParam(value = "searchType") String searchType) {
        Integer userId = redisService.getUserId(token);
        AssertionUtil.notNull(keyword, ErrorCode.BIZ_PARAM_ILLEGAL, "keyword不能为空!");
        List<SearchVO> searchVOList = new ArrayList<>();
        searchVOList.addAll(friendService.getFriendSearchVoList(userId, searchType, keyword));
        searchVOList.addAll(groupService.getGroupSearchVoList(userId, searchType, keyword));
        searchVOList.addAll(channelService.getChannelSearchVoList(userId, searchType, keyword));
        return Response.createSuc(searchVOList);
    }
}
