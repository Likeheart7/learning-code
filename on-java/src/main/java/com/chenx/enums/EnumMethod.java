package com.chenx.enums;

import java.lang.reflect.Modifier;
import java.text.DateFormat;
import java.util.Date;

/**
 * 为枚举类型实现方法
 */
public enum EnumMethod {
    DATETIME("time") {
        @Override
        String getInfo() {
            return DateFormat.getDateInstance().format(new Date());
        }
    },
    CLASSPATH("classpath") {
        @Override
        String getInfo() {
            return System.getenv("CLASSPATH");
        }
    };
    private final String name;

    EnumMethod(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    abstract String getInfo();

    public static void main(String[] args) throws Exception {
        System.out.println(EnumMethod.DATETIME.getName() + ": " + EnumMethod.DATETIME.getInfo());

        // 枚举的成员变量默认修饰符
        System.out.println(Modifier.toString(EnumMethod.class.getDeclaredField("name").getModifiers())); // private final
        System.out.println(Modifier.toString(EnumMethod.class.getDeclaredConstructor(String.class, int.class, String.class).getModifiers())); // 默认private，虚拟机默认加两个参数
    }
}
