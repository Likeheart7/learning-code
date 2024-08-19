package com.chenx;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 一个演示tryLock方法的Demo。
 */
public class LockExample {
    // 默认是可重入的非公平锁
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        // 第一个线程，执行5秒
        new Thread(() -> {
            try {
                lock.lock();
                System.out.println("线程1拿到了锁，开始执行任务1");
                Tools.pause(15, TimeUnit.SECONDS);
                System.out.println("任务1执行完成。即将释放锁资源");
            } finally {
                lock.unlock();
            }
        }, "task---1").start();

        //第二个线程，尝试获取锁3s，获取不到就执行失败
        new Thread(() -> {
            try {
                if (lock.tryLock(3, TimeUnit.SECONDS)) {
                    try {
                        System.out.println("线程二成功获取锁");
                        Tools.pause(8000);
                        System.out.println("任务二执行完成");
                    } finally {
                        lock.unlock();
                    }
                } else {
                    System.out.println("线程二超出等待时间，放弃执行任务2");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "task---2").start();
        //第三个线程，尝试获取锁7s，获取不到就执行失败
        new Thread(() -> {
            try {
                if (lock.tryLock(20, TimeUnit.SECONDS)) {
                    try {
                        System.out.println("线程3成功获取锁");
                        Tools.pause(10000);
                        System.out.println("任务3执行完成");
                    } finally {
                        lock.unlock();
                    }
                } else {
                    System.out.println("线程3超出等待时间，放弃执行任务3");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "task---3").start();
    }
}
