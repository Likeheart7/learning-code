package com.chenx.exception

fun methodRequire(msg: String) {
    require(msg.length > 1) { "message.length < 1" } // 类似guava提供的Preconditions校验类，失败时抛出异常
}

fun methodRequireNotNull(msg: String?) {
    requireNotNull(msg) // 校验成功自动转成String
    println(msg.length)
}


fun main() {
    methodRequire("你好")
    methodRequireNotNull("你好")
}