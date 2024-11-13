package com.chenx.juc;

import com.sun.istack.internal.NotNull;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 基于AQS自定义的不可重入锁
 */
public class CustomNonReentrantLock implements Lock, Serializable {
    // 辅助类，JUC中一般也是通过这个模式来自定义工具的。
    private static class Sync extends AbstractQueuedSynchronizer {
        // 锁是否被持有

        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        /**
         * 如果state为0，尝试获取锁。
         */
        @Override
        protected boolean tryAcquire(int acquires) {
            assert acquires == 1;
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread()); // 设置独占锁的持有线程
                return true;
            }
            // 获取失败
            return false;
        }

        /**
         * 尝试释放锁，设置state为0
         */
        @Override
        protected boolean tryRelease(int releases) {
            assert releases == 1;
            // state为0时，锁违背持有，直接抛出异常
            if (getState() == 0)
                throw new IllegalMonitorStateException();
            setExclusiveOwnerThread(null); // 删除持有线程
            setState(0); // 重置state为0
            return true;
        }

        // 提供条件变量接口
        Condition newCondition() {
            return new ConditionObject();
        }
    }


    // 该自定义的Sync用来做具体的工作
    private final Sync sync = new Sync();


    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(timeout));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    public boolean isLocked() {
        return sync.isHeldExclusively();
    }
}
