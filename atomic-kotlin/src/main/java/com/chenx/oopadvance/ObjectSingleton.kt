package com.chenx.oopadvance

// 使用object关键字定义单例
object Singleton {
    val n = 2;
    val name = "单例"
    fun f() = n * 2
}

fun main() {
//    var singleton = Singleton() // 不能实例化
    println(Singleton.name) // 直接调用
}