package com.project.gimme.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Gilbert
 * @date 2022/2/24 17:03
 */
public class StreamUtil {
    public static void copy(InputStream inputStream, OutputStream outputStream) {
        byte[] buffer = new byte[1024];
        int len = 0;
        try {
            len = inputStream.read(buffer);
            while (len != -1) {
                outputStream.write(buffer, 0, len);
                len = inputStream.read(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
