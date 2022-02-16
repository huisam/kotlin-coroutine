package com.huisam.kotlincoroutine.chapter4

import kotlinx.coroutines.*
import java.util.concurrent.Executors
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val duration = measureTimeMillis {
        val job = CoroutineScope(Dispatchers.Default).launch {
            try {
                while (isActive) {
                    delay(500)

                    println("running job")
                }
            } finally {
                // 취소중인 코루틴은 일시 중단 되지 않으므로 실행되지 않는다. 따라서 반드시 실행하는 NonCancellable 을 붙인다
                withContext(NonCancellable) {
                    println("cancelled")
                    delay(1000)
                    println("delay completed bye")
                }
            }
        }
        delay(1200)
        job.cancelAndJoin()
    }
    println("Took $duration ms")


    val handler = CoroutineExceptionHandler { _, throwable ->
        println("error : ${throwable.message}")
    }
    CoroutineScope(Dispatchers.Default + handler).launch {
        println("Starting in ${Thread.currentThread().name}")
        TODO("Not implementation")
    }.join()

    val dispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
    val name = withContext(dispatcher) {
        println("Starting in ${Thread.currentThread().name}")
        "Susan"
    }
    println("User : $name")
}