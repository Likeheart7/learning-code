package com.chenx;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

public class UnsafeEx {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);
        long offset = unsafe.objectFieldOffset(AtomicInteger.class.getDeclaredField("value"));
        System.out.println("AtomicInteger#value属性的偏移量是：" + offset);
    }

}
