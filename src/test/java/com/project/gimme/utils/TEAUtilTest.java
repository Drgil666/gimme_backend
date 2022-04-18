package com.project.gimme.utils;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author Gilbert
 * @date 2022/4/18 16:33
 */
public class TEAUtilTest {

    @Test
    public void encrypt() {
        String text = "这是一条消息\uD83D\uDE01\uD83D\uDE01";
        byte[] bytes = TEAUtil.encryptByTea(text);
        System.out.println(Arrays.toString(bytes));
        String text2 = TEAUtil.decryptByTea(bytes);
        System.out.println(text2);
    }

    @Test
    public void decrypt() {
    }
}