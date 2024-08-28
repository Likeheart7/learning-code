package com.chenx.chapter02.ops

class Multiply(private val left:Number, private val right:Number) {
    private val result get() = left * right
    fun toNumber() = Number(result)
    override fun toString() = "$left * $right"


}