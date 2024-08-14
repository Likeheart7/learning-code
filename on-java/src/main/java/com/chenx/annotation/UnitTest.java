package com.chenx.annotation;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class UnitTest {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        processor(UnitExample.class);
    }

    public static <T> void processor(Class<T> c) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = c.getDeclaredMethods();
        for (Method method : methods) {
            Test annotation = method.getAnnotation(Test.class);
            if (annotation != null) {
                Object result = method.invoke(new UnitExample());
                System.out.println(result);
            }
        }

    }
}

