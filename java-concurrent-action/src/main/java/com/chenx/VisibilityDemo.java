/*
授权声明：
本源码系《Java多线程编程实战指南（核心篇）》一书（ISBN：978-7-121-31065-2，以下称之为“原书”）的配套源码，
欲了解本代码的更多细节，请参考原书。
本代码仅为原书的配套说明之用，并不附带任何承诺（如质量保证和收益）。
以任何形式将本代码之部分或者全部用于营利性用途需经版权人书面同意。
将本代码之部分或者全部用于非营利性用途需要在代码中保留本声明。
任何对本代码的修改需在代码中以注释的形式注明修改人、修改时间以及修改内容。
本代码可以从以下网址下载：
https://github.com/Viscent/javamtia
http://www.broadview.com.cn/31065
*/
package com.chenx;

/**
 * 这里作者要展示的是因为JVM的优化问题，导致
 * <pre>
 *  while (!toCancel) {
 *      if (doExecute()) {
 *           break;
 *      }
 * }
 * </pre>
 * 被优化成
 * <pre>
 *  if (! toCancel) {
 *      while (true) {
 *          if (doExecute()) {
 *              break;
 *          }
 *      }
 * }
 * </pre>
 * <p>所以需要通过给toCancel变量添加volatile关键字来让JVM意识到该变量可能被多个线程改变。</p>
 * <p>但是实际上本人在测试中并没有发现被优化成和作者一样的代码，可能和jdk版本有关。</p>
 * <p>所以本代码仅作记录和解释，无演示用意。</p>
 */
public class VisibilityDemo {

    public static void main(String[] args) throws InterruptedException {
        TimeConsumingTask timeConsumingTask = new TimeConsumingTask();
        Thread thread = new Thread(timeConsumingTask);
        thread.start();
        Thread.sleep(5000);
        timeConsumingTask.cancel();
    }
}

class TimeConsumingTask implements Runnable {
    private boolean toCancel = false;

    @Override
    public void run() {
        while (!toCancel) {
            if (doExecute()) {
                break;
            }
        }
        if (toCancel) {
            System.out.println("Task was canceled.");
        } else {
            System.out.println("Task done.");
        }
    }

    private boolean doExecute() {
        boolean isDone = false;
        System.out.println("executing...");

        // 模拟实际操作的时间消耗
        Tools.randomPause(50);
        // 省略其他代码

        return isDone;
    }

    public void cancel() {
        toCancel = true;
        System.out.println(this + " canceled.");
    }
}

