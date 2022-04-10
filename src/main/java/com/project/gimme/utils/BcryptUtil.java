package com.project.gimme.utils;

import org.mindrot.jbcrypt.BCrypt;

/**
 * @author DrGilbert
 * @date 2022/1/4 12:14
 */
public class BcryptUtil {
    /**
     * 密码加密
     *
     * @param password 要加密的密码
     * @return 加密好的密码
     */
    public static String encode(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(13));
    }

    /**
     * 比对密码是否正确
     *
     * @param password 明文
     * @param hash     密文
     * @return 是否匹配
     */
    public static Boolean checkPassword(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }

    private static Integer random(Integer min, Integer max) {
        return (Integer) (int) (Math.random() * (max - min) + min);
    }
}
