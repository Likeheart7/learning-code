package com.chenx.basic

import java.util.*

/*
定义 方法名(参数名: 参数类型): 返回类型
 */
fun multiplyByTwo(x: Int): Int {
    println("Inside MultiplyByTwo")
    return x * 2
}

// 如果函数不提供有意义的结果，它的返回类型是 Unit。可以省略
// 只有一行函数体的可以使用等号语法，等号语法可以基于类型推断省略返回类型
fun goodbye(): Unit = println("goodbye")

// 可变参数
fun varargs(vararg args: String) {
    println(args.contentToString())
}

fun main() {
    val r = multiplyByTwo(5)
    println(r)
    goodbye()
    // 无返回值
    val u1: Unit = println("abc")
    println(u1) // kotlin.Unit
    // 可变参数
    varargs()
    varargs("a", "b")
    varargs("a", "b", "c", "d", "e")
}