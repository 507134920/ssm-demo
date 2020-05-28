package com.ssm.util;

import java.util.Random;

/**
 * 随机获取一组6位数字的字符串
 */
public class Number {
    public static String getNumber(){
        String sources = "0123456789"; // 加上一些字母，就可以生成pc站的验证码了
        Random rand = new Random();
        StringBuffer flag = new StringBuffer();
        for (int j = 0; j < 6; j++)
        {
            flag.append(sources.charAt(rand.nextInt(9)) + "");
        }
        return flag.toString();
    }
}
