package com.chenx.exception

import java.io.File


// kotlin通过use()实现资源回收，简化在finally中回收的方式
fun main() {
    val file = File("C:\\Users\\chenxing\\Desktop\\node.txt")
    // use() 可以与实现了 Java 的 AutoCloseable 接口的任何对象一起使用。它执行块内的代码，然后在对象上调用 close()
    file.bufferedReader()
        .use { println(it.readLine()) }
    // useLines() 打开一个 File 对象，提取所有的行，并将这些行传递给目标函数
   file.useLines {
       it.filter { n -> n.length > 5 } .forEach { line -> println(line) }
   }
    // forEachLine() 对文件中的每一行应用一个操作
    file.forEachLine { println(it) }
}