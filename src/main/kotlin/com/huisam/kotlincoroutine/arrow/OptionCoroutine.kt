package com.huisam.kotlincoroutine.arrow

import arrow.core.*
import arrow.core.continuations.option

suspend fun value(): Option<Int> = option {
    val a = Some(1).bind() // 1 중단 지점
    val b = Some(1 + a).bind() // 2 중단 지점
    val c = Some(1 + b).bind() // 3 중단 지점
    a + b +c
}

suspend fun main() {
    println(value())
}