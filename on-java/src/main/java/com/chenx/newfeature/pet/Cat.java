package com.chenx.newfeature.pet;

public class Cat implements Pet {
    @Override
    public void feed() {
        System.out.println("喂猫");
    }

    public void walk() {
        System.out.println("猫猫走路");
    }
}
