package com.chenx.oopadvance

class Message(text: String) {
    val content :String
        get() = "message content: $this.text"
    // 类似Java的非静态代码块
    init {
        println("init method...")
    }
}

fun main() {
    Message("message text info")
    Message("message text info")
}