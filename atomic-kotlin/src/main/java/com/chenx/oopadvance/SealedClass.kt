package com.chenx.oopadvance

/*
 密封类
 sealed 类的所有直接子类必须位于与基类相同的文件中。
 sealed 类基本上是一个带有额外约束的 abstract 类
 */

sealed class Transport

data class Bus(
    val speed: Int
) : Transport()

data class Train(
    val speed: Int
) : Transport()

private data class Plane(
    val speed: Int
) : Transport()

fun transfor(transport: Transport):String =
    //
    when (transport) {
        // 密封类使编译器了解所有可能的子类型，when语句用作表达式时，无需添加else语句
        is Train -> "Train speed is at ${transport.speed}"
        is Bus -> "Bus speed is at ${transport.speed}"
        is Plane -> "Plane speed is at ${transport.speed}"
    }


fun main() {
//    Transport() // 不能直接实例化

    println(transfor(Plane(600)))
    // 密封类的子类可以枚举出来,需要启用反射
//    Transport::class.sealedSubclasses.forEach{ println("$it") }
}

