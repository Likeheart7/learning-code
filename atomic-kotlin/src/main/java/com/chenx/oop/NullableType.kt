package com.chenx.oop

fun main() {
    val map = mapOf(1 to "first", 2 to "second")
    println(map[3]) // null

    // kotlin默认不允许为空
//    val num1:Int = null     // 直接报错
    // 如果可能为空，需要加上?
    val num2: Int? = null
    var num3: Int = 3
//    num3 = num2 // 不能将可空类型的标识符赋值给非可空类型的标识符
    // 判断非空后，可以讲可空类型赋给不可空类型
    if (num2 != null) {
        num3 = num2 // 自动从Int?转型为Int
    }
    println(num3::class.simpleName)
//    println(num2!!::class.simpleName)   // Exception in thread "main" java.lang.NullPointerException


    // 关于可空类型的操作
    println("======== 关于可空类型的操作 ========")
    val str: String? = null;
//    str.length // Only safe (?.) or non-null asserted (!!.) calls are allowed on a nullable receiver of type String?
    println(str?.length) // null ?.在为null时直接返回null，不会执行调用操作

    // ?:
    val str2: String? = null ?: "abc"; // 如果?:左侧为null，返回右侧，如果不是，返回左侧
    println(str2) // "abc"

    // 通过可空类型的扩展函数判断变量
    println("\n==== isNullOrBlank() & isNullOrEmpty()")
    val str3: String? = null
    println(str3.isNullOrBlank())
    println(str3.isNullOrEmpty())
}