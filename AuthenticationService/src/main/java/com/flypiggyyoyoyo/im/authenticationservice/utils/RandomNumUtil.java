package com.flypiggyyoyoyo.im.authenticationservice.utils;

import java.util.Random;

public class RandomNumUtil {
    public static String getRandomNum() {
        Random random = new Random();
        // 生成一个 100000 到 999999 之间的随机整数
        int randomNum = random.nextInt(900000)+100000;
        // 将生成的随机整数格式化为 6 位字符串，不足 6 位时前面补 0
        return String.format("%06d", randomNum);
    }
}
