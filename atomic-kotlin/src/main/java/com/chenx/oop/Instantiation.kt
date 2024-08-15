package com.chenx.oop

// 顶级属性
val globalVar = "global variable"

class Dog(
    // 将 name 定义为 var 或 val 时，它将成为一个属性
    val name: String
) {
    // 属性是属于类的 var 或 val。
    var kind = "dog";


    // 成员函数，属于类
    fun bark() = "Woof!"

    override fun toString(): String {
        return formatOutput()
    }

    private fun formatOutput() = "name: $name, kind: $kind"
}

fun main() {
    val dog = Dog("yang")
    println(dog) // 没重写toString，是com.chenx.oop.Dog@378bf509
    println(dog.bark())
    println(dog.name)
    println(dog.kind)
    println(globalVar)
//    println(dog.formatOutput()) // Cannot access 'formatOutput': it is private in 'Dog'
}