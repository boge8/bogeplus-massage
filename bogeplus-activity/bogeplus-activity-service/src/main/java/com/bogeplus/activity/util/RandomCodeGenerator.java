package com.bogeplus.activity.util;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomCodeGenerator {

    private static final String CHARACTERS = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789"; // 不包含容易混淆的字符
    private static final int CODE_LENGTH = 4;
    private static final Random random = new Random();
    private static final Set<String> generatedCodes = new HashSet<>(); // 用于存储已生成的码，避免重复

    public static String generateUniqueCode() {
        String code;
        do {
            StringBuilder sb = new StringBuilder(CODE_LENGTH);
            for (int i = 0; i < CODE_LENGTH; i++) {
                int index = random.nextInt(CHARACTERS.length());
                sb.append(CHARACTERS.charAt(index));
            }
            code = sb.toString();
        } while (generatedCodes.contains(code)); // 检查是否重复
        generatedCodes.add(code); // 添加到已生成集合中
        return code;
    }

    // 用于测试的方法
    /*public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(generateUniqueCode());
        }
    }*/
}
