package com.huisam.kotlincoroutine.arrow

import arrow.core.*


private fun maybeItWillReturnSomething(flag: Boolean): Option<String> {
    return if (flag) Some("Found Value") else None
}

fun main() {
    val value1 = maybeItWillReturnSomething(false).getOrElse { "No Value" }
    println("No Value" == value1)

    val noValue: Option<Double> = None
    val value2 = when (noValue) {
        is Some -> noValue.value
        is None -> 0.0
    }
    println(value2 == 0.0)

    val some = 1.some()
    val none = none<Int>()

    println(some.map { it * 3 } == 3.some())
    println(none.map { it * 3 } == none<Int>())

    Option
}