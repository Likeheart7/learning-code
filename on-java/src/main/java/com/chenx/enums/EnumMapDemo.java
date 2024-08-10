package com.chenx.enums;

import java.util.EnumMap;

/**
 * EnumMap是只能用某个枚举作为key的Map。
 */
public class EnumMapDemo {
    public static void main(String[] args) {
        var map = new EnumMap<AlarmPoints, Command>(AlarmPoints.class);
        map.put(AlarmPoints.BATHROOM, () -> {
            System.out.println("bathroom fire!");
        });
        map.put(AlarmPoints.KITCHEN, () -> {
            System.out.println("kitchen fire!");
        });
        for (var key : map.keySet()) {
            map.get(key).action();
            map.get(key).print();
        }
    }
}

interface Command {
    void action();

    /**
     * 默认方法示例
     */
    default void print() {
        System.out.println("default method");
    }
}

enum AlarmPoints {
    KITCHEN, BATHROOM, BEDROOM;
}
