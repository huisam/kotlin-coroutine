package com.huisam.kotlincoroutine.chapter2

import kotlinx.coroutines.*

fun doSomething() {
    throw UnsupportedOperationException("Not supported yet")
}

@OptIn(ExperimentalCoroutinesApi::class)
fun main() = runBlocking {
    val task = CoroutineScope(Dispatchers.Default).async {
        doSomething()
    }
    task.join()
    if (task.isCancelled) {
        val exception = task.getCompletionExceptionOrNull()
        println("Error with message: ${exception?.message}")
    } else {
        println("Success")
    }
    println("completed")
}