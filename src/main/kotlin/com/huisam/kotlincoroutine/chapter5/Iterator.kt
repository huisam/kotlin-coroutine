package com.huisam.kotlincoroutine.chapter5

fun main() {
    val iterator = iterator {
        yield(1)
        yield(10L)
        yield("Hello")
    }

    while (iterator.hasNext()) {
        println(iterator.next())
    }
}