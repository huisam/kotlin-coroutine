package com.huisam.kotlincoroutine.chapter3

import kotlinx.coroutines.*

@OptIn(InternalCoroutinesApi::class)
fun main() = runBlocking {
    val job1 = CoroutineScope(Dispatchers.Default).launch {
        delay(3000)
    }

    delay(2000)
    job1.cancel(cause = CancellationException("Cancel for timeout"))

    val cancellation = job1.getCancellationException()
    println(cancellation.message)
    println("job1 active: ${job1.isActive}")
    println("job1 cancelled: ${job1.isCancelled}")
    println("job1 completed: ${job1.isCompleted}")

    val job2 = CoroutineScope(Dispatchers.Default).launch {
        delay(1000)
    }
    job2.join()
    println("job2 active: ${job2.isActive}")
    println("job2 cancelled: ${job2.isCancelled}")
    println("job2 completed: ${job2.isCompleted}")

    val started = job2.start()
    println("job2 started again? $started")
    job2.join()
}