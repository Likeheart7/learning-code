package com.chenx;

/**
 * {@link InheritableThreadLocal} 支持子线程访问父线程的线程变量的ThreadLocal子类
 */
public class InheritableThreadLocalEx {
    public static final InheritableThreadLocal<String> THREAD_LOCAL = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        THREAD_LOCAL.set("main thread local variable..");
        new Thread(() -> {
            // Inheritable允许子线程访问父线程存入的数据
            // sub thread[sub-thread] value: main thread local variable..
            System.out.println("sub thread[" + Thread.currentThread().getName() +"] value: " + THREAD_LOCAL.get());
        }, "sub-thread").start();
    }
}
