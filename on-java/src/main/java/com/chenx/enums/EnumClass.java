package com.chenx.enums;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.TreeSet;

public class EnumClass {
    public static void main(String[] args) {
//        callMethod();
//        System.out.println();
//        checkValues(Color.class);
//        checkValues(Color.class.getSuperclass())
    }

    /**
     * 调用枚举类的一些方法
     */
    private static void callMethod() {
        for (Color color : Color.values()) {
            System.out.println(color.name());
            System.out.println(color.ordinal());    // 顺序
            System.out.println(color.getDeclaringClass());
            System.out.println(color.compareTo(Color.PINK));
            System.out.println(color == Color.PINK);    // 可能是true
            System.out.println();
        }
        System.out.println(Color.valueOf("pink".toUpperCase()));
        System.out.println(Color.class.getSuperclass()); // class   自动继承的

        System.out.println(Arrays.toString(Color.class.getEnumConstants()));
    }

    /**
     * 查看values()方法的来源
     */
    private static void checkValues(Class<?> clazz) {
        System.out.println("=====>>>analyzing class Color");
        System.out.println("Interfaces: " + Arrays.toString(clazz.getGenericInterfaces()));
        System.out.println("Base: " + clazz.getSuperclass());
        TreeSet<String> methods = new TreeSet<>();
        for (Method m : clazz.getMethods()) {
            methods.add(m.getName());
        }
        System.out.println("Methods: " + methods);
    }

}
