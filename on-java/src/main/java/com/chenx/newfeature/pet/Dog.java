package com.chenx.newfeature.pet;

public class Dog implements Pet {
    @Override
    public void feed() {
        System.out.println("喂狗");
    }

    public void shout() {
        System.out.println("狗叫");
    }
}
