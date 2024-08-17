package com.chenx.oopadvance

// 构造器重载
// 主构造函数必须始终被调用
class ConstructorOverload(i: Int) {
    // 所有次构造器必须通过:来调用主构造器
    // 辅助构造函数调用另一个构造函数（使用 this）必须发生在额外的构造函数逻辑之前
    constructor(c: Char) : this(c - 'A') {
        println("second constructor")
    }
    constructor(s:String): this(s.toInt()) {
        println("third constructor")
    }
}

fun main() {
    ConstructorOverload(1)
    ConstructorOverload('S')
    ConstructorOverload("100")
}