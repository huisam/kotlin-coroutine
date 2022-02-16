package com.huisam.kotlincoroutine.chapter4

import kotlinx.coroutines.*
import java.util.concurrent.Executors

/**
 * Dispachers 목록
 *  - Unconfined: 첫 번째 중단 지점에 도달할 때까지만 현재 스레드에 코루틴을 실행 ( main -> otherThread )
 *  - singleThread: 단일 스레드로 코루틴을 실행
 *  - fixedThread: 주어진 스레드 풀에서 코루틴을 실행
 */
fun main() = runBlocking {
    CoroutineScope(Executors.newSingleThreadExecutor().asCoroutineDispatcher()).launch {
        println("Starting in ${Thread.currentThread().name}")
        delay(500)
        println("Starting in ${Thread.currentThread().name}")
    }.join()

    CoroutineScope(Executors.newFixedThreadPool(5).asCoroutineDispatcher()).launch {
        println("Starting in ${Thread.currentThread().name}")
        delay(500)
        println("Starting in ${Thread.currentThread().name}")
    }.join()

    CoroutineScope(Dispatchers.Unconfined).launch {
        println("Starting in ${Thread.currentThread().name}")
        delay(500)
        println("Starting in ${Thread.currentThread().name}")
    }.join()


}

