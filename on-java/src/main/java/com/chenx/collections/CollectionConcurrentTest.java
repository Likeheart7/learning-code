package com.chenx.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程情况下：
 * 并发对ArrayList进行add，会出现数据丢失或扩容时的ArrayIndexOutOfBoundsException
 * 并发对HashMap进行put，会出现数据丢失的情况，也可能程序卡死
 */
public class CollectionConcurrentTest {

    public static void main(String[] args) throws InterruptedException {
//        ArrayListConcurrentAdd();
//        COWArrayListConcurrentAdd();
//        ConcurrentHashMapConcurrentPut();
//        AtomicIntegerConcurrent();
        for (int i = 0; i < 1000; i++) {
            HashMapConcurrentPut();
        }
    }

    /**
     * 多线程插入ArrayList会出现插入丢失的问题，也可能是出现ArrayIndexOutOfBoundsException
     */
    private static void ArrayListConcurrentAdd() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(20);
        ArrayList<Integer> list = new ArrayList<>();
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                list.add(i);
            }
            countDownLatch.countDown();
        };
        for (int i = 0; i < 20; i++) {
            new Thread(task).start();
        }
        countDownLatch.await();
        System.out.println(list.size());
    }

    /**
     * 多线程插入CopyOnWriteArrayList不会出现插入丢失的问题。
     */
    private static void COWArrayListConcurrentAdd() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(20);
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                list.add(i);
            }
            countDownLatch.countDown();
        };
        for (int i = 0; i < 20; i++) {
            new Thread(task).start();
        }
        countDownLatch.await();
        System.out.println(list.size());
    }

    /**
     * 多线程插入HashMap会出现插入数据丢失的问题
     */
    private static void HashMapConcurrentPut() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(20);
        HashMap<String, Integer> map = new HashMap<>();
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                map.put(i + Thread.currentThread().getName(), i);
            }
            countDownLatch.countDown();
        };
        for (int i = 0; i < 20; i++) {
            new Thread(task).start();
        }
        countDownLatch.await();
        System.out.println(map.size());
    }

    /**
     * 多线程插入ConcurrentHashMap不会出现插入数据丢失的问题
     */
    private static void ConcurrentHashMapConcurrentPut() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(20);
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                map.put(i + Thread.currentThread().getName(), i);
            }
            countDownLatch.countDown();
        };
        for (int i = 0; i < 20; i++) {
            new Thread(task).start();
        }
        countDownLatch.await();
        System.out.println(map.size());
    }


    /**
     * 使用AtomicInteger处理售票问题
     */
    private static void AtomicIntegerConcurrent() throws InterruptedException {
        AtomicInteger atomicTickets = new AtomicInteger(200000);
        CountDownLatch countDownLatch = new CountDownLatch(20);
        AtomicInteger count = new AtomicInteger(0);
        Runnable task = () -> {
            while (atomicTickets.getAndDecrement() > 0) {
//                System.out.println(Thread.currentThread().getName() + ": 售出第" + count.incrementAndGet() + "张票");
                count.incrementAndGet();
            }
            countDownLatch.countDown();
        };
        for (int i = 0; i < 20; i++) {
            new Thread(task).start();
        }
        countDownLatch.await();
        System.out.println(count.get());
    }
}
