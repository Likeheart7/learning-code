package com.chenx.oopadvance

//SAM: Single Abstract Method 单一抽象方法，就是是Java中的函数式接口
// kotlin中定义的方式
fun interface Action {
    fun action()
}

fun doAction(action: Action) {
    action.action()
}

fun main() {
    doAction { print("hello") } // 可以直接传递lambda表达式，无需创建实现了Action接口的对象
}