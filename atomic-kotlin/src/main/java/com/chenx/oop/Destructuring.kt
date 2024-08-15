package com.chenx.oop

/**
 * 使用Pair返回两个值
 */
fun compute(input: Int): Pair<Int, String> =
    if (input > 0) Pair(input * 2, "Positive")
    else Pair(input, "Negative")

// 数据类自动支持解构,顺序是属性定义顺序
data class Computation(
    val data: Int,
    val desc: String,
    val param: Double
)

fun Computation.describe() = "description"

class NormalClass(
    val name: String,
    val age: Int,
    val sex: Int
) {
    // 普通类的解构需要定义ComponentN()，能解构几个取决于定义的方法
    operator fun component1(): String = name;
    operator fun component2(): Int = age;
}


fun main() {
    // 使用解构获取返回值
    val (number, desc) = compute(10)
    println("$number, $desc")
    // 解构对象
    val obj = Computation(1, "Computation Object", 3.14)
    val (data, info, param) = obj
    println("$data, $info, $param")
    println(obj.describe())

    val person = NormalClass("chenxing", 25, 0)
    val (name, age) = person
    println("$name, $age")
}