package com.huisam.kotlincoroutine.flow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.runBlocking
import java.util.concurrent.atomic.AtomicLong

fun main(): Unit = runBlocking(Dispatchers.Default) {
    val flowStartTime = AtomicLong(0L)

    flow {
        repeat(times = 3) { data ->
            delay(300L)
            produceLog(flowStartTime.get(), data)
            emit(data)
        }
    }.onStart {
        flowStartTime.set(System.currentTimeMillis())
    }.collectLatest { data ->
        delay(700L)
        consumerLog(flowStartTime.get(), data)
    }
}

private fun produceLog(flowStartTime: Long, data: Int) {
    println(
        """
            Time: ${elapsedFromStartTime(flowStartTime)}ms / Producer Coroutine : ${Thread.currentThread().name} / data : $data [>>]
        """.trimIndent()
    )
}

private fun consumerLog(flowStartTime: Long, data: Int) {
    println(
        """
            Time: ${elapsedFromStartTime(flowStartTime)}ms / Consumer Coroutine : ${Thread.currentThread().name} / data : $data [<<]
        """.trimIndent()
    )
}

private fun elapsedFromStartTime(flowStartTime: Long) = System.currentTimeMillis() - flowStartTime