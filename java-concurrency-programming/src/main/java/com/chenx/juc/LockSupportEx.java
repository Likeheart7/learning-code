package com.chenx.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * {@link java.util.concurrent.locks.LockSupport} 提供park和unpark方法来挂起唤醒指定线程
 * 底层使用unsafe#park/unpark
 */
public class LockSupportEx {
    public static void main(String[] args) throws InterruptedException {
        /*
        before park...
        main thread before unpark...
        after unpark...
         */
        Thread task = new Thread(() -> {
            System.out.println("before park...");
            LockSupport.park();
            System.out.println("after unpark...");
        });
        task.start();
        TimeUnit.SECONDS.sleep(3);
        System.out.println("main thread before unpark...");
//        LockSupport.unpark(task);
        // 线程的interrupt方法也会唤醒调用park的线程，所以在某些情况下这就是park方法的虚假唤醒
        task.interrupt();
    }
}
