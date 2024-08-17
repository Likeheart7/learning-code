package com.chenx.oopadvance

/**
 * kotlin的嵌套类通过外部类名称来访问，类似Java静态内部类
 */

class Airport(private val code: String) {
    open class CivilPlane{
        // 内部类可以访问外部类的private成员
        fun contact(airport: Airport) = airport.code
    }
}

fun main() {
    val airport = Airport("DDEEBB")
    var plane = Airport.CivilPlane()
    println(plane.contact(airport))
}