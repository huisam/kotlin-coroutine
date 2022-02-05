package com.huisam.kotlincoroutine.chapter1

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

suspend fun createCoroutines(amount: Int) {
    val jobs = arrayListOf<Job>()
    for (i in 1..amount) {
        jobs += CoroutineScope(Dispatchers.Default).launch {
            println("Start $i in ${Thread.currentThread().name}")
            delay(1000)
            println("End $i in ${Thread.currentThread().name}")
        }
    }
    jobs.forEach { it.join() }
}

fun main() = runBlocking {
    println("${Thread.activeCount()} thread active counts start")
    val time = measureTimeMillis {
        createCoroutines(10_000)
    }
    println("${Thread.activeCount()} thread active counts end")
    println("Take $time ms")
}