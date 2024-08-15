package com.chenx.oop

import com.chenx.oop.atomictest.eq

fun main() {
    listContainer()
    setContainer()
    mapContainer()
}

fun mapContainer() {
    println("========= Map method =========")
    val mapOfNumber = mapOf(
        "five" to 5,
        "four" to 4,
        "three" to 3
    )
    println(mapOfNumber)
    println(mapOfNumber["five"])
    println(mapOfNumber.keys)
    println(mapOfNumber.values)
    // 遍历键值对
    for (entry in mapOfNumber) {
        println("${entry.key} = ${entry.value}")
    }
    // 迭代解包
    for ((key, value) in mapOfNumber){
        println("$key to $value")
    }
    // 可变Map
    val mutableMap = mutableMapOf<String, Int>()
    mutableMap["sugar"] = 1
    mutableMap["Jam"] = 2
    println(mutableMap)
    mutableMap += ("fire" to 9) // +=操作在原可变Map上进行
    println(mutableMap)
}

/**
 * Set容器的表示
 */
fun setContainer() {
    println("========= Set methods ========")
    val sets = setOf(1, 1, 2, 2, 3, 3)
    println(sets)
    setOf(1, 3, 2) eq sets // true，顺序不影响比较
    println(9 in sets) // false
    // String直接转Set
    println("ontology".toSet())

    // 可变Set
    val mutableSet = mutableSetOf(1, 1, 1, 3, 2, 4)
    mutableSet.add(1)
    mutableSet += 1
    mutableSet.add(5)
    println(mutableSet)
}

/**
 * list列表的演示
 */
fun listContainer(){
    println("========= List methods ========")
    // 不可变列表
    val number = listOf(1, 2 ,33, 4)
    println(number)
    number eq "[1, 2, 33, 4]"
    val sortedNumber = number.sorted() // 返回排序后的新数组
    println(sortedNumber)
    // 显式指定类型
    val digits: List<Int> = listOf(99, 91, 81, 71, 44, 97)
    println(digits)
    // 尝试修改不可变列表
    println(number[2])
//    number[2] = 2;  // No set method providing array access
    // 可变列表
    val mutableList = mutableListOf<Int>(99, 86, 97, 59, 29)
    println(mutableList)
    // 修改可变列表
    mutableList[0] = 97
    println(mutableList)
}