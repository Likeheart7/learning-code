package com.chenx.newfeature;

public class PatternMatching {
    public static void main(String[] args) {
        instanceOfPattern("abcd");
        oddScope("abcd");
    }

    private static void instanceOfPattern(Object obj) {
        // 当instanceof判断成功时，s就是类型转换后的结果，也叫智能转型，只能用在 && 中
        if (obj instanceof String s && !s.isEmpty()) {
            System.out.println(s.length());
        }
    }

    /**
     * 异常情况下的一种奇怪的作用域
     */
    private static void oddScope(Object obj) {
        if (!(obj instanceof String s)) {
            System.out.println("not a string");
            throw new RuntimeException();   // 当存在异常时，在合理的作用域外，还能访问到 智能转型后的s
        }
        System.out.println(s.length());
    }
}
