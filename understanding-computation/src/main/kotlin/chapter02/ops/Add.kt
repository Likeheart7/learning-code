package com.chenx.chapter02.ops

class Add(private val left:Number, private val right: Number) {
    val result get() = left + right

    override fun toString() = "$left + $right"
}