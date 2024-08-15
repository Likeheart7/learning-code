package com.chenx.oop

// 枚举类，和Java类似
enum class Color {
    RED, WHITE, BROWN, BLACK, PURPLE;
}

fun main() {
    val colors = Color.entries
    for (color in colors) {
        // when表达式，可以理解成switch加强版
        when(color){
            Color.PURPLE -> println(Color.PURPLE.ordinal)
            Color.RED -> println(Color.RED.ordinal)
            Color.BROWN -> println(Color.BROWN.ordinal)
            Color.BLACK -> println(Color.BLACK.ordinal)
            else -> println("I do not know the color")
        }
    }

    println(colors.toList())
}