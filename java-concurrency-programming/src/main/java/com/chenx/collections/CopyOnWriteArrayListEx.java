package com.chenx.collections;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListEx {
    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("1"); // 对COW的写操作会加锁，且获取一份数组拷贝，在拷贝上操作，操作完更换掉原来的数组
        list.add("2");
        list.add("3");
        list.add("4");
        Iterator<String> iter = list.iterator(); // 弱一致性的迭代器，先获取迭代器，之后其他线程删除，此时迭代器持有的是获取迭代器时候的快照
        Thread task = new Thread(() -> {
            list.remove(2);
            list.remove(0);
        });
        task.join();
        // 1 2 3 4
        while (iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }
    }
}
