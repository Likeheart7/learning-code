package com.chenx.random;

import java.util.concurrent.ThreadLocalRandom;

public class ThreadLocalRandomEx {
    public static void main(String[] args) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < 5; i++) {
            System.out.println(random.nextInt(10));
        }
    }
}
