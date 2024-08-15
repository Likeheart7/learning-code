package com.chenx.oop

import com.chenx.oop.atomictest.eq

/**
 * data class 数据类，
 * 会自动生成全参构造器、equals、hashCode方法
 */
data class Sample(
    var arg1 : String,
    var arg2 : Int
)

fun main() {
    val s1 = Sample("chenx", 24)
    val s2 = Sample("chenx", 24)
    println(s1) // Sample(arg1=chenx, arg2=24)
    println(s1 == s2) // true
    println(s1.hashCode() == s2.hashCode())

    val s3 = s1.copy(arg1 = "like") // copy会拷贝一个新对象，可以在参数中重新声明部分属性值
    println(s3)
    println(s3.hashCode())
}
