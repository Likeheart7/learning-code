package com.chenx.atomic;

import java.util.concurrent.atomic.LongAdder;

/**
 * 虽然AtomicLong提供了多线程下的原子操作保证，但是在多线程竞争的情况下，会有大量线程CAS失败不断自旋而浪费CPU时间，
 * 所以提供了LongAdder.
 * LongAdder在多线程时，将值切分到cells数组的多个Cell元素内，每个更新操作更新某个Cell，实现更高的并发度
 * 数组cells源自父类Striped64，同时对Cell类使用@sun.misc.Contended注解进行缓存行填充，使得各个Cell元素不会因为分布
 * 在同一个缓存行，而出现伪共享的问题（伪共享：同缓存行的多个变量因为其中一个变量的更新而导致整个缓存行失效）
 */
public class LongAdderEx {
    public static void main(String[] args) {
        // 初始值是0
        LongAdder longAdder = new LongAdder();
        System.out.println(longAdder.longValue());
        longAdder.add(20);
        System.out.println(longAdder.longValue());
    }
}
