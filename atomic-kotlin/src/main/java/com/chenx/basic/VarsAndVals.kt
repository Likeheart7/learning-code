package com.chenx.basic

fun main() {
    var num1 = 10;
    val num2 = 20;
    val obj = arrayListOf("A", "b", "c")
    num1 = 30;
//    num2 = 30;  // val定义的值不允许更改
    obj[0] = "f";
    println(num1)
    println(num2)
    println(obj) // 可以修改val变量的内部成员，只是不能更改引用指向，类似Java的final
}
