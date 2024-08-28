package com.chenx.chapter02.ops


class Number(private val value: Int) {
    operator fun plus(inc: Number) = value + inc.value
    operator fun times(inc: Number) = value * inc.value

    override fun toString() = "<<$value>>";
}