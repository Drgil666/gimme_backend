package com.project.gimme.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;

/**
 * @author Gilbert
 * @date 2022/2/24 15:55
 */
public class BucketUtil {
    public static final String BUCKET_FILE_ATTRIBUTE = "bucket_file";
    public static final String BUCKET_IMAGE_ATTRIBUTE = "bucket_image";
    public static final String CREATE_TIME_ATTRIBUTE = "create_time";
    public static final String FILE_TYPE_ATTRIBUTE = "file_type";

    @AllArgsConstructor
    @Getter
    public enum FileType {
        /**
         * 普通文件类型
         */
        TYPE_BUCKET_FILE(0, BUCKET_FILE_ATTRIBUTE),
        /**
         * 图片类型
         */
        TYPE_BUCKET_IMAGE(1, BUCKET_IMAGE_ATTRIBUTE);
        private final Integer code;
        private final String name;
    }

    public static final FileType[] FILE_TYPE_LIST = FileType.values();
    public static final HashMap<String, Integer> FILE_TYPE_MAP = getFileTypeMap();

    public static HashMap<String, Integer> getFileTypeMap() {
        HashMap<String, Integer> hashMap = new HashMap<>(10);
        for (FileType fileType : FILE_TYPE_LIST) {
            hashMap.put(fileType.name, fileType.code);
        }
        return hashMap;
    }

    public static Integer getFileTypeByName(String name) {
        if (FILE_TYPE_MAP.containsKey(name)) {
            return FILE_TYPE_MAP.get(name);
        }
        return null;
    }
}
