package com.huisam.kotlincoroutine.chapter7

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.actor
import kotlin.system.measureTimeMillis

sealed class CounterMessage
object IncreaseCounter : CounterMessage()
class GetCounter(val response: CompletableDeferred<Int>) : CounterMessage()

fun CoroutineScope.counterActor() = actor<CounterMessage> {
    var count = 0

    for (msg in channel) {
        when (msg) {
            is IncreaseCounter -> count++
            is GetCounter -> msg.response.complete(count)
        }
    }
}

suspend fun CoroutineScope.massiveRun(action: suspend () -> Unit) {
    val n = 100
    val k = 1000
    val time = measureTimeMillis {
        val jobs = List(n) {
            coroutineScope {
                launch {
                    repeat(k) {
                        action()
                    }
                }
            }
        }
        jobs.forEach { it.join() }
    }
    println("Completed ${n * k} actions in $time ms")
}


fun main() {
    runBlocking {
        val counter = counterActor()
        coroutineScope {
            massiveRun {
                counter.send(IncreaseCounter)
            }
        }

        val response = CompletableDeferred<Int>()
        counter.send(GetCounter(response))
        println("response = ${response.await()}")
        counter.close()
    }
}