package com.chenx.fp

fun main() {
    lambda()
    collectionOps()
}

fun collectionOps() {
    println("\n========== lambda集合操作 =========")
    // 使用lambda创建集合
    // 实际上，这个{it}是List()这个方法的第二个参数，只不过kotlin的风格将lambda参数写在括号外
    println(List(10){it})   // List()和MutableList()不是构造函数，而是函数。它们的名称故意以大写字母开头，以使它们看起来像构造函数。
    println(List(10) { it })
    println(List(10){0})
    println(List(10){'a' + it})
    val list = List(5){it}
    println(list.find { it > 10 }) // 返回值可能是null
    println(list.count { it > 10 }) // 0
    println(list.none { it > 10 }) // true
    println(list.all { it < 10 }) // true
}


fun lambda() {
    println("\n====== lambda ========")
    val list = listOf(1, 2, 3, 4)
//    val result = list.map { n: Int -> "[$n]" }
    // 可以简化
//    val result = list.map {n -> "[$n]"}
    // 如果只有一个参数，会自动生成名为it的参数名，所以还可以简化
    val result = list.map { "[$it]" }
    println(result)
    println(list.joinToString(", ") { "[$it]" })
    // 带有多个参数的lambda
    println(list.mapIndexed { index, ele ->  "[$index to $ele]"  })
    println(list.indices.map { "[$it]" })
    val list2 = (1..5).toList()
    // 过滤出偶数
    println("偶数：" + list2.filter { it % 2 == 0 })
    println("any操作结果：" + (1..10).toList().any { it > 9 })
}
