package com.project.gimme.controller;


import com.project.gimme.annotation.LoginAuthorize;
import com.project.gimme.exception.ErrorCode;
import com.project.gimme.pojo.Channel;
import com.project.gimme.pojo.ChatFile;
import com.project.gimme.pojo.Group;
import com.project.gimme.pojo.User;
import com.project.gimme.pojo.vo.ChatFileVO;
import com.project.gimme.pojo.vo.CudRequestVO;
import com.project.gimme.pojo.vo.Response;
import com.project.gimme.service.*;
import com.project.gimme.utils.AssertionUtil;
import com.project.gimme.utils.ChatMsgUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
    @Resource
    private UserService userService;
    @Resource
    private GroupService groupService;
    @Resource
    private ChannelService channelService;

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
    @ApiOperation(value = "获取聊天文件")
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
    public Response<ChatFile> uploadFile(@ApiParam(value = "加密验证参数")
                                         @RequestHeader(TOKEN) String token,
                                         @ApiParam(value = "上传的文件")
                                         @RequestParam(value = "file")
                                                 MultipartFile file,
                                         @ApiParam(value = "会话类型")
                                         @RequestParam(value = "chatType")
                                                 String chatType,
                                         @ApiParam(value = "会话id")
                                         @RequestParam(value = "objectId")
                                                 Integer objectId) throws IOException {
        Integer userId = redisService.getUserId(token);
        String mongoId = gridFsService.createFile(file);
        if (!StringUtils.isEmpty(mongoId)) {
            ChatFile chatFile = new ChatFile();
            chatFile.setTimestamp(new Date());
            chatFile.setOwnerId(userId);
            chatFile.setSize(file.getSize() * 8);
            //单位为bit
            chatFile.setMongoId(mongoId);
            chatFile.setType(chatType);
            chatFile.setObjectId(objectId);
            chatFile.setFilename(file.getOriginalFilename());
            if (chatFileService.createChatFile(chatFile)) {
                return Response.createSuc(chatFile);
            }
        }
        return Response.createErr(ErrorCode.BIZ_PARAM_ILLEGAL.getCode(), "上传失败!");
    }

    @ResponseBody
    @PostMapping("/upload/avatar")
    @ApiOperation(value = "上传头像")
    @LoginAuthorize()
    public Response<String> uploadAvatar(@ApiParam(value = "加密验证参数")
                                         @RequestHeader(TOKEN) String token,
                                         @ApiParam(value = "上传的文件")
                                         @RequestParam(value = "file")
                                                 MultipartFile file,
                                         @ApiParam(value = "会话类型")
                                         @RequestParam(value = "chatType")
                                                 String chatType,
                                         @ApiParam(value = "会话id")
                                         @RequestParam(value = "objectId")
                                                 Integer objectId) throws IOException {
        Integer userId = redisService.getUserId(token);
        String mongoId = gridFsService.createFile(file);
        if (StringUtils.isEmpty(mongoId)) {
            return Response.createErr(ErrorCode.BIZ_PARAM_ILLEGAL.getCode(), "上传失败!");
        }
        if (chatType.equals(ChatMsgUtil.Character.TYPE_FRIEND.getName())) {
            User user = userService.getUser(userId);
            user.setAvatar(mongoId);
            userService.updateUser(user);
            return Response.createSuc(null);
        } else if (chatType.equals(ChatMsgUtil.Character.TYPE_GROUP.getName())) {
            Group group = groupService.getGroup(objectId);
            group.setAvatar(mongoId);
            groupService.updateGroup(group);
            return Response.createSuc(null);
        } else if (chatType.equals(ChatMsgUtil.Character.TYPE_CHANNEL.getName())) {
            Channel channel = channelService.getChannel(objectId);
            channel.setAvatar(mongoId);
            channelService.updateChannel(channel);
            return Response.createSuc(null);
        }
        return Response.createErr("更新失败!");

    }

    @ResponseBody
    @GetMapping("/download")
    @ApiOperation(value = "下载文件")
    @LoginAuthorize()
    public void downloadFile(@ApiParam(value = "加密验证参数")
                             @RequestHeader(TOKEN) String token,
                             @ApiParam(value = "文件id")
                             @RequestParam(value = "chatFileId") Integer chatFileId, HttpServletResponse response) {
        AssertionUtil.notNull(chatFileId, ErrorCode.BIZ_PARAM_ILLEGAL, "chatFileId不能为空!");
        ChatFile chatFile = chatFileService.getChatFile(chatFileId);
        try {
            GridFsResource file = gridFsService.getFile(chatFile.getMongoId());
            InputStream inputStream = file.getInputStream();
            response.setContentType(file.getContentType());
            response.setHeader("content-disposition", "attachment;filename=\"" + file.getFilename() + "\"");
            response.setHeader("file-name", file.getFilename());
            OutputStream outputStream = response.getOutputStream();
            byte[] buffer = new byte[1024];
            int len = inputStream.read(buffer);
            while (len != -1) {
                outputStream.write(buffer, 0, len);
                len = inputStream.read(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ResponseBody
    @GetMapping(value = {"/image/{mongoId}", "/image"})
    @ApiOperation(value = "下载文件")
    public void downloadImage(@ApiParam(value = "文件的mongoId")
                              @PathVariable(required = false, value = "mongoId") String mongoId,
                              HttpServletResponse response) throws IOException {
        if (StringUtils.isEmpty(mongoId)) {
            mongoId = "624a901452eea04231b3f855";
        }
        GridFsResource file = gridFsService.getFile(mongoId);
        InputStream inputStream = file.getInputStream();
        response.setContentType(file.getContentType());
        response.setHeader("content-disposition", "attachment;filename=\"" + file.getFilename() + "\"");
        response.setHeader("file-name", file.getFilename());
        OutputStream outputStream = response.getOutputStream();
        byte[] buffer = new byte[1024];
        int len = inputStream.read(buffer);
        while (len != -1) {
            outputStream.write(buffer, 0, len);
            len = inputStream.read(buffer);
        }
    }
}
