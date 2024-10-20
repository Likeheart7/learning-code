package com.chenx;

/**
 * 伪共享问题：即因为L1缓存操作单元是一个缓存行，可以包含多个变量，某个变量的失效会导致整个缓存行失效。
 */
public class FalseSharingEx {
    public static final int LINE_NUM = 8172;
    public static final int COLUM_NUM = 8172;
    public static void main(String[] args) {
        sharing();
        falseSharing();
    }

    private static void sharing() {
        long[][] array = new long[LINE_NUM][COLUM_NUM];
        long start = System.currentTimeMillis();
        for (int i = 0; i < LINE_NUM; i++) {
            for (int j = 0; j < COLUM_NUM; j++) {
                array[i][j] = i*2+j;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("single thread cache time: " + (end - start) + "ms");
    }

    private static void falseSharing() {
        long[][] array = new long[LINE_NUM][COLUM_NUM];
        long start = System.currentTimeMillis();
        for (int i = 0; i < COLUM_NUM; i++) {
            for (int j = 0; j < LINE_NUM; j++) {
                // 这种每次访问同列不同行的数据不适用局部性原理
                // 所以测试结果每次都比另一个方法慢，并且数组越大，越明显
                array[j][i] = i*2+j;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("single thread no cache time: " + (end - start) + "ms");
    }
}
