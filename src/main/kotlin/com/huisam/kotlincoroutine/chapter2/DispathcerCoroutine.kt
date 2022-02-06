package com.huisam.kotlincoroutine.chapter2

import kotlinx.coroutines.*
import java.util.concurrent.Executors

fun printCurrentThread() {
    println("Running in thread [${Thread.currentThread().name}]")
}

fun main() = runBlocking {
    val dispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
    val task = CoroutineScope(dispatcher).launch {
        printCurrentThread()
    }
    task.join()
}