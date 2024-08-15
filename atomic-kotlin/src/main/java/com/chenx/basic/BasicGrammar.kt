package com.chenx.basic

fun main() {
    ifGrammar(false)
    println(ifGrammar2(false))
    whileGrammar()
    //  .. 语法会将两个边界都包含在结果范围内。
    forGrammar(1..3)
    //  until 排除了结束点
    forGrammar(1 until 3)
    // downTo 提供降序，step设定步长
    forGrammar(5 downTo 3 step 2)
    // 字符范围
    for (c in 'a'..'g')
        println(c)
}

fun forGrammar(r: IntProgression) {
    for (i in r) {
        println("in for loop $i")
    }
}

fun whileGrammar() {
    var cnt = 0;
    while (cnt++ < 5) {
        println("int while loop: $cnt")
    }
}

// if表达式基于表达式生成的带有返回值的方法。可以省略返回值类型、return关键字
fun ifGrammar2(b: Boolean) = if (b) "True" else "False"

fun ifGrammar(condition: Boolean) {
    if (condition) {
        println("condition is true")
    } else {
        println("condition is not true")
    }
}
