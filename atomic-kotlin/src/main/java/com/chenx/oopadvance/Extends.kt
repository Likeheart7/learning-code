package com.chenx.oopadvance

import java.time.LocalDate
import java.util.concurrent.CountDownLatch

// 基类必须是open修饰的，否则默认不可继承
open class Base {
    protected var name:String = "A"
    // 函数必须是open的否则不能重写
    open fun baseMethod():String="base method"
}

class Derived:Base() {
    override fun baseMethod(): String {
        println("子类可以访问protect修饰的父类成员：${super.name}")
        println(super.baseMethod() + " is wrong")

        return "we call it function"
    }
}

fun main() {
    println(Derived().baseMethod())
}
