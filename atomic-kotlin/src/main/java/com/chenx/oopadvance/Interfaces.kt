package com.chenx.oopadvance

// 接口定义
interface Computer {
    // 接口提供的属性必须被实现类覆盖
    val symbol: Char

    // 提供给实现类实现的方法
    fun prompt(): String
    fun calculateAnswer(): Int
}

// 实现类
class Desktop : Computer {
    override val symbol: Char = 'W'
    override fun prompt(): String = "Desktop thinking..."

    override fun calculateAnswer(): Int = 100
}

class DeepThought() : Computer {
    override val symbol: Char
        get() = 'D'
    override fun prompt(): String = "银河系漫游者"

    override fun calculateAnswer(): Int = 42
}


fun main() {
    val computers = listOf(Desktop(), DeepThought())
    println(computers.map { it.prompt() })
    println(computers.map { it.calculateAnswer() })
}