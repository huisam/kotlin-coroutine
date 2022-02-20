package com.huisam.kotlincoroutine.chapter5

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce

/**
 * Producer 는 Channel 을 이용해서 코루틴의 동작한 결과값을 주고 받는다.
 * 채널이 전부 수신되었는데 receive 를 시도하면 ClosedReceiveChannelException 이 발생한다.
 * 채널에 대해서는 chapter 6 에 더 알아보자.
 */
@OptIn(ExperimentalCoroutinesApi::class)
fun main() = runBlocking {
    val producer: ReceiveChannel<Any> = CoroutineScope(Dispatchers.Default).produce {
        for (i in 0..1) {
            send("sending $i")
        }
    }
    println(producer.isClosedForReceive)
    println(producer.isEmpty)
    producer.consumeEach {
        println(it)
    }
    println(producer.isClosedForReceive)
    println(producer.isEmpty)
}