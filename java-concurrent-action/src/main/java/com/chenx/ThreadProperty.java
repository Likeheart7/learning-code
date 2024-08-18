package com.chenx;

public class ThreadProperty {
    public static void main(String[] args) {
        basicProperty();
    }

    private static void basicProperty() {
        Thread curThread = Thread.currentThread();
        System.out.println("线程id：" + curThread.getId());
        System.out.println("线程名称：" + curThread.getName());
        System.out.println("是否是守护线程：" + curThread.isDaemon());
        System.out.println("线程优先级：" + curThread.getPriority());
        System.out.println("线程状态：" + curThread.getState());
    }

}