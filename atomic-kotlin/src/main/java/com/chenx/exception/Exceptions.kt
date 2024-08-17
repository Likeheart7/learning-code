package com.chenx.exception

class MyException(private val value:String) : Exception("自定义异常:$value")

fun main() {
    try {
        throw MyException("异常抛出")
    } catch (e:Exception) {
        println(e.message)
    } finally {
        println("finally ... ")
    }
}