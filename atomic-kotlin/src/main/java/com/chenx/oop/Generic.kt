package com.chenx.oop

/**
 * 通过泛型，一个类可以持有不同类型的属性
 */
class GenericHolder<T>(
    private val a: T
) {
    fun getValue() = a
}

data class Person(val name:String, val age:Int)
data class Pet(val name:String, val kind: String)

// 泛型函数定义泛型类型
fun <T> genericFunc(arg:T) = arg.toString()

//泛型扩展函数，需要声明被扩展类的泛型
fun <T> List<T>.first(): T {
    if (isEmpty())
        throw NoSuchElementException("Empty List")
    return this[0]
}

// 扩展属性，需要自定义getter
val Person.retireAge : Int
    get() = 65 - this.age

fun main() {
    val p = Person("guocn", 25)
    val holder1 = GenericHolder(p)
    val holder2 = GenericHolder(Pet("过长南", "dog"))
    // 返回类型可以自动转型
    println(holder1.getValue())
    println(holder2.getValue())
    println(genericFunc(Person("gcn", 25)))
    println(p.retireAge)
}