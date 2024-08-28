package com.chenx.chapter02

import com.chenx.chapter02.ops.Add
import com.chenx.chapter02.ops.Multiply
import com.chenx.chapter02.ops.Number

fun main() {
    val add = Add(
        Multiply(Number(1), Number(2)).toNumber(),
        Multiply(Number(1), Number(2)).toNumber()
    )
    println(add)
}