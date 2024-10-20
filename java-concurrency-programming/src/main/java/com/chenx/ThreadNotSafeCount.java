package com.chenx;

/**
 * 1. iinc是int类型的局部变量才有的字节码
 * 2. lconst_1：将long类型的1放入操作数栈
 */
public class ThreadNotSafeCount {
    private long value;
    public Long getCount() {
        return value;
    }
    public void inc() {
        /*
         2 getfield #2 <com/chenx/ThreadNotSafeCount.value : J>
         5 lconst_1
         6 ladd
         7 putfield #2 <com/chenx/ThreadNotSafeCount.value : J>
         */
        ++value;
    }

}
