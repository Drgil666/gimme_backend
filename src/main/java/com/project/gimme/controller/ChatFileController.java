package com.project.gimme.controller;


import com.project.gimme.annotation.LoginAuthorize;
import com.project.gimme.exception.ErrorCode;
import com.project.gimme.pojo.ChatFile;
import com.project.gimme.pojo.vo.ChatFileVO;
import com.project.gimme.pojo.vo.CudRequestVO;
import com.project.gimme.pojo.vo.FileVO;
import com.project.gimme.pojo.vo.Response;
import com.project.gimme.service.ChatFileService;
import com.project.gimme.service.GridFsService;
import com.project.gimme.service.RedisService;
import com.project.gimme.utils.AssertionUtil;
import com.project.gimme.utils.ChatMsgUtil;
import com.project.gimme.utils.StreamUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
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
@RequestMapping("/api/chat/file")
@Api(tags = "聊天文件")
public class ChatFileController {
    @Resource
    private RedisService redisService;
    @Resource
    private ChatFileService chatFileService;
    @Resource
    private GridFsService gridFsService;

    @ResponseBody
    @PostMapping()
    @ApiOperation(value = "创建/更新/删除聊天文件")
    @LoginAuthorize()
    public Response<ChatFile> cudChatFile(@ApiParam(value = "加密验证参数")
                                          @RequestHeader(TOKEN) String token,
                                          @ApiParam(value = "包含用户信息，操作信息")
                                          @RequestBody CudRequestVO<ChatFile, Integer> request) {
        Integer userId = redisService.getUserId(token);
        switch (request.getMethod()) {
            case CudRequestVO.CREATE_METHOD: {
                if (chatFileService.createChatFile(request.getData())) {
                    return Response.createSuc(request.getData());
                } else {
                    return Response.createErr(ErrorCode.INNER_PARAM_ILLEGAL.getCode(), "好友关系已存在!");
                }
            }
            case CudRequestVO.UPDATE_METHOD: {
                if (chatFileService.updateChatFile(request.getData()) == 1) {
                    return Response.createSuc(request.getData());
                } else {
                    return Response.createErr(ErrorCode.INNER_PARAM_ILLEGAL.getCode(),
                            "更新失败!");
                }
            }
            case CudRequestVO.DELETE_METHOD: {
                if (chatFileService.deleteChatFile(request.getKey()) > 0) {
                    for (Integer id : request.getKey()) {
                        ChatFile chatFile = chatFileService.getChatFile(id);
                        gridFsService.deleteFile(chatFile.getMongoId());
                        //清楚对应的文件数据
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
    public Response<ChatFile> getChatFile(@ApiParam(value = "加密验证参数")
                                          @RequestHeader(TOKEN) String token,
                                          @ApiParam(value = "文件id")
                                          @RequestParam(value = "fileId") Integer fileId) {
        Integer userId = redisService.getUserId(token);
        AssertionUtil.notNull(fileId, ErrorCode.BIZ_PARAM_ILLEGAL, "fileId不能为空!");
        ChatFile chatFile = chatFileService.getChatFile(fileId);
        if (chatFile != null) {
            return Response.createSuc(chatFile);
        } else {
            return Response.createErr("获取好友关系失败!");
        }
    }

    @ResponseBody
    @GetMapping("/list")
    @ApiOperation(value = "获取文件列表")
    @LoginAuthorize()
    public Response<List<ChatFile>> getChatFileByObjectId(@ApiParam(value = "加密验证参数")
                                                          @RequestHeader(TOKEN) String token,
                                                          @ApiParam(value = "聊天类型")
                                                                  String type,
                                                          @ApiParam(value = "朋友/群聊/频道id类型")
                                                          @RequestParam(value = "objectId") Integer objectId,
                                                          @ApiParam(value = "关键词")
                                                          @RequestParam(value = "keyword", required = false, defaultValue = "")
                                                                  String keyword) {
        Integer userId = redisService.getUserId(token);
        AssertionUtil.notNull(objectId, ErrorCode.BIZ_PARAM_ILLEGAL, "objectId不能为空!");
        AssertionUtil.notNull(type, ErrorCode.BIZ_PARAM_ILLEGAL, "type不能为空!");
        Integer chatType = ChatMsgUtil.getCharacterByName(type);
        AssertionUtil.notNull(chatType, ErrorCode.BIZ_PARAM_ILLEGAL, "type类型错误!");
        List<ChatFile> chatFileList = chatFileService.getChatFileByObjectId(chatType, objectId, keyword);
        if (chatFileList != null) {
            return Response.createSuc(chatFileList);
        } else {
            return Response.createErr("获取好友关系失败!");
        }
    }

    @ResponseBody
    @GetMapping("/list/info")
    @ApiOperation(value = "获取文件列表")
    @LoginAuthorize()
    public Response<List<ChatFileVO>> getChatFileVoByObjectId(@ApiParam(value = "加密验证参数")
                                                              @RequestHeader(TOKEN) String token,
                                                              @ApiParam(value = "聊天类型")
                                                                      String type,
                                                              @ApiParam(value = "朋友/群聊/频道id类型")
                                                              @RequestParam(value = "objectId") Integer objectId,
                                                              @ApiParam(value = "关键词")
                                                              @RequestParam(value = "keyword", required = false, defaultValue = "")
                                                                      String keyword) {
        Integer userId = redisService.getUserId(token);
        AssertionUtil.notNull(objectId, ErrorCode.BIZ_PARAM_ILLEGAL, "objectId不能为空!");
        AssertionUtil.notNull(type, ErrorCode.BIZ_PARAM_ILLEGAL, "type不能为空!");
        List<ChatFileVO> chatFileVoList = chatFileService.getChatFileVoByObjectId(type, userId, objectId, keyword);
        if (chatFileVoList != null) {
            return Response.createSuc(chatFileVoList);
        } else {
            return Response.createErr("获取好友关系失败!");
        }
    }

    @ResponseBody
    @PostMapping("/upload")
    @ApiOperation(value = "上传文件")
    @LoginAuthorize()
    public Response<FileVO> uploadFile(@ApiParam(value = "加密验证参数")
                                       @RequestHeader(TOKEN) String token,
                                       @ApiParam(value = "包含用户信息，操作信息")
                                               MultipartFile file, String chatType, Integer objectId, String fileName, String fileType) throws IOException {
        Integer userId = redisService.getUserId(token);
        String mongoId = gridFsService.createFile(file, fileName, fileType);
        if (!StringUtils.isEmpty(mongoId)) {
            ChatFile chatFile = new ChatFile();
            chatFile.setTimestamp(new Date());
            chatFile.setOwnerId(userId);
            chatFile.setSize(file.getSize());
            chatFile.setMongoId(mongoId);
            chatFile.setType(chatType);
            chatFile.setObjectId(objectId);
            chatFile.setFilename(fileName);
            if (chatFileService.createChatFile(chatFile)) {
                return Response.createSuc(null);
            }
        }
        return Response.createErr(ErrorCode.BIZ_PARAM_ILLEGAL.getCode(), "上传失败!");
    }

    @ResponseBody
    @GetMapping("/download")
    @ApiOperation(value = "下载文件")
    @LoginAuthorize()
    public void downloadFile(@ApiParam(value = "加密验证参数")
                             @RequestHeader(TOKEN) String token,
                             @ApiParam(value = "文件id")
                             @RequestParam(value = "mongoId") String mongoId, HttpServletResponse response) {
        AssertionUtil.notNull(mongoId, ErrorCode.BIZ_PARAM_ILLEGAL, "mongoId不能为空!");
        try {
            InputStream inputStream = gridFsService.getFile(mongoId);
            StreamUtil.copy(inputStream, response.getOutputStream());
        } catch (IOException e) {
            try {
                response.getOutputStream().write(0);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
