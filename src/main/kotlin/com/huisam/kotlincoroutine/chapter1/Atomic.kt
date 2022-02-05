package com.huisam.kotlincoroutine.chapter1

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

var counter = 0

fun asyncIncrement(count: Int) = CoroutineScope(Dispatchers.Default).async {
    for (i in 0 until count) {
        counter++
    }
}

// 코루틴은 동시에 처리가 가능하므로 이는 원자성 위반에 대한 코드이다
fun main() = runBlocking {
    val workerA = asyncIncrement(2000)
    val workerB = asyncIncrement(100)
    workerA.await()
    workerB.await()
    println("counter : $counter")
}