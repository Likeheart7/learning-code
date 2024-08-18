package com.chenx;

import java.util.Random;

/**
 * Runnable和Thread创建线程的区别
 * 运行程序，Thread实现的输出中，所有的count都是是不符合推测的100
 */
public class DifferentTwoWay {


    static class Counter {
        //        private final AtomicInteger count = new AtomicInteger();
        private int count = 0;

        public void increment() {
//            count.incrementAndGet();
            count++;
        }

        public int value() {
//            return count.get();
            return count;
        }
    }

    private static void randomSleep(int time) {
        try {
            Thread.sleep(new Random().nextInt(time));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class CustomThread extends Thread {
        private final Counter counter = new Counter();

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                randomSleep(80);
                counter.increment();
            }
            System.out.println("CustomThread: " + counter.value());
        }
    }

    static class CustomTask implements Runnable {
        private final Counter counter = new Counter();

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                randomSleep(80);
                counter.increment();
            }
            System.out.println("CustomTask: " + counter.value());
        }
    }

    public static void main(String[] args) {
        // 本机八核十六线程，所以这里是16
        System.out.println(Runtime.getRuntime().availableProcessors());
        Thread t;
        CustomTask ct = new CustomTask();
        final int numOfProcessors = Runtime.getRuntime().availableProcessors();
        // 因为竞态原因，以下两种方式的应当都是一个小于2*16*100 = 3200的数字
        // 但是实际上这里只有Runnable实现是符合猜测的，而Thread实现的结果是所有输出后面的数字都是100
        // 将Counter的count属性改为AtomicInteger后，只有Runnable实现方式的结果符合最终3200的猜测。而Thread实现的，结果仍然全是100
        for (int i = 0; i < 2 * numOfProcessors; i++) {
            // new Thread
            t = new Thread(ct);
            t.start();
        }
        for (int i = 0; i < 2 * numOfProcessors; i++) {
            // new Thread
            t = new CustomThread();
            t.start();
        }
    }

}