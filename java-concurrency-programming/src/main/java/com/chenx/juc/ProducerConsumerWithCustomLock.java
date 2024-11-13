package com.chenx.juc;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

/**
 * 基于自定义的独占锁{@link com.chenx.juc.CustomNonReentrantLock}的生产者消费者模型
 */
public class ProducerConsumerWithCustomLock {
    final static CustomNonReentrantLock lock = new CustomNonReentrantLock();
    final static Condition notFull = lock.newCondition();
    final static Condition notEmpty = lock.newCondition();
    final static int QUEUE_SIZE = 10;
    final static Queue<String> queue = new LinkedBlockingQueue<>(QUEUE_SIZE);

    public static void main(String[] args) {
        Thread producer = new Thread(() -> {
            lock.lock();
            try {
                while (queue.size() == QUEUE_SIZE) {
                    notEmpty.await();
                }
                queue.add("product");
                notFull.signal();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        });

        Thread consumer = new Thread(() -> {
            lock.lock();
            try {
                while (queue.isEmpty()) {
                    notFull.await();
                }
                String product = queue.poll();
                System.out.println("consumer consume [" + product + "]");
                notEmpty.signal();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        });

        producer.start();
        consumer.start();


    }
}
