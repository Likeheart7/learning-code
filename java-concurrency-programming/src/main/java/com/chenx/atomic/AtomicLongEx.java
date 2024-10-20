package com.chenx.atomic;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicLong;

public class AtomicLongEx {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        AtomicLong value = new AtomicLong(3L);
        System.out.println(value.getAndIncrement());
        System.out.println(value.get());
        // getAndIncrement基于Unsafe实现
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);
//        long offset = unsafe.objectFieldOffset(AtomicLong.class.getDeclaredField("value"));
        // AtomicLong内部也维护了value属性的偏移量
        Field valueOffsetField = AtomicLong.class.getDeclaredField("valueOffset");
        valueOffsetField.setAccessible(true);
        long offset = (long) valueOffsetField.get(value);
        // getAndAddLong参数为：对象，属性偏移量，增加值
        System.out.println(unsafe.getAndAddLong(value, offset, 2)); // 4
        System.out.println(value.get());    // 6
    }
}
