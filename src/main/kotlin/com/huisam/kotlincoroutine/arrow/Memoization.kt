package com.huisam.kotlincoroutine.arrow

import arrow.core.memoize
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue


fun recursiveFib(n: Long): Long {
    return if (n < 2) n else recursiveFib(n - 1) + recursiveFib(n - 2)
}

@OptIn(ExperimentalTime::class)
fun main() {
    var memoizedFib: (Long) -> Long = { it }
    memoizedFib = { n: Long -> if (n < 2) n else memoizedFib(n - 1) + memoizedFib(n - 2) }.memoize()

    val memoizationResult = measureTimedValue { memoizedFib(45) }
    val recursiveResult = measureTimedValue { recursiveFib(45) }
    println("Memoization: ${memoizationResult.duration}")
    println("Recursive: ${recursiveResult.duration}")
}