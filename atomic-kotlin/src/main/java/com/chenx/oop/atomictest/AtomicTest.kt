package com.chenx.oop.atomictest

import kotlin.math.abs
import kotlin.reflect.KClass

const val ERROR_TAG = "[Error]: "

private fun <L, R> test(
    actual: L,
    excepted: R,
    checkEquals: Boolean = true,
    predicate: () -> Boolean
) {
    println(actual)
    if (!predicate()) {
        print(ERROR_TAG)
        println("$actual " + (if (checkEquals) "!=" else "==") + " $excepted")
    }
}

/**
 * 使用infix定义的可以中缀使用的函数 exp eq excepted
 * 比较字符串
 */
infix fun Any.eq(rval: String) {
    test(this, rval) {
        toString().trim() == rval.trimIndent()
    }
}

/**
 * 比较对象相等
 */
infix fun <T> T.eq(rval: T) {
    test(this, rval) {
        this == rval
    }
}

/**
 * 比较对象是否不相等
 */
infix fun <T> T.neq(rval: T) {
    test(this, rval, checkEquals = false) {
        this != rval
    }
}

/**
 * 比较两个Double是否近似相等
 */
infix fun Double.eq(rval: Double) {
    test(this, rval) {
        abs(this - rval) < 0.0000001
    }
}

/**
 * 保存捕获的异常信息
 */
class CapturedException(
    private val exceptionClass: KClass<*>?,
    private val actualMessage: String
) {
    private val fullMessage: String
        get() {
            val className =
                exceptionClass?.simpleName ?: ""
            return className + actualMessage
        }

    infix fun eq(message: String) {
        fullMessage eq message
    }

    infix fun contains(parts: List<String>) {
        if (parts.any { it !in fullMessage }) {
            print(ERROR_TAG)
            println("Actual message: $fullMessage")
            println("Expected parts: $parts")
        }
    }

    override fun toString() = fullMessage
}

/**
 * 保存异常并生成该异常相关信息
 *    capture {
 *      // 出现异常的代码
 *    } eq "FailureException: message"
 */
fun capture(f: () -> Unit): CapturedException =
    try {
        f()
        CapturedException(
            null,
            "$ERROR_TAG Expected an exception"
        )
    } catch (e: Throwable) {
        CapturedException(e::class,
            (e.message?.let { ": $it" } ?: ""))
    }

/**
 * 在以下位置调用时累积输入：
 *   trace("info")
 *   trace(object)
 * 稍后将累积的结果与预期进行比较：
 *   trace eq "expected output"
 */
object trace {
    private val trc = mutableListOf<String>()
    operator fun invoke(obj: Any?) {
        trc += obj.toString()
    }

    /**
     * 将trc内容与多行进行比较
     * `String` 会忽略空格
     */
    infix fun eq(multiline: String) {
        val trace = trc.joinToString("\n")
        val expected = multiline.trimIndent()
            .replace("\n", " ")
        test(trace, multiline) {
            trace.replace("\n", " ") == expected
        }
        trc.clear()
    }
}