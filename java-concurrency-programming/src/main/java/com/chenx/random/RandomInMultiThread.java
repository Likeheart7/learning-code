package com.chenx.random;

import java.util.Random;

/**
 * <pre>
 * 在多线程下使用单个Random实例生成随机数时，当多个线程同时计算随机数来计算新的种子时，
 * 多个线程会竞争同一个原子变量的更新操作，由于原子变量的更新是CAS操作，同时只有一个线程会成功，
 * 所以<em>会造成大量线程进行自旋重试</em>
 * </pre>
 */
public class RandomInMultiThread {
    public static void main(String[] args) {
        Random random = new Random();
        System.out.println(random.nextInt(5));
    }
}
