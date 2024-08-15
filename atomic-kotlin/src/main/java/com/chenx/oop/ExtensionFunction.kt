package com.chenx.oop

class People(private var name: String = "like") {
    // val只能有get不能有set
    val peopleName: String
        get() = name.uppercase()

    // var类型的成员属性，可以有setter，也可以直接字面量赋值
    var identityName = name
        get() = name
        set(value) {
            field = value
            println("field gets $value")
        }
}

// 扩展函数
fun People.formatName() = "name: $peopleName"

fun main() {
    val p1 = People("heart")
    println(p1.formatName())
}