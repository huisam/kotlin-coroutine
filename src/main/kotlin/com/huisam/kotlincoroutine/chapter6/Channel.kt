package com.huisam.kotlincoroutine.chapter6

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel

/**
 * 실행중인 쓰레드에 상관없이 코루틴 간의 메세지를 보내기 위한 객체
 *
 */
fun main(): Unit = runBlocking {
    // 버퍼가 없는 채널. 수신자가 receive 를 호출하기 전까지 send 를 호출해도 대기한다
    val rendezvousChannel = Channel<Int>()
    CoroutineScope(Dispatchers.Default).launch {
        repeat(10) {
            rendezvousChannel.send(it)
            println("$rendezvousChannel send $it")
        }
    }
    rendezvousChannel.receive()
    rendezvousChannel.receive()

    // 버퍼가 무한한 채널. 수신자가 receive 호출 여부 상관없이 send 호출이 가능
    // 이걸 사용하면 실제 outOfMemory 가 발생할 수 있으므로 권장하지 않음
    val unlimitedChannel = Channel<Int>(Channel.UNLIMITED)
    CoroutineScope(Dispatchers.Default).launch {
        repeat(10) {
            unlimitedChannel.send(it)
            println("$unlimitedChannel send $it")
        }
    }
    delay(500)

    // capacity 에 정해진 용량만큼 send 호출이 가능. queue size 가 넉넉하다고 판단되면 다시 send 호출
    val arrayChannel = Channel<Int>(5)
    CoroutineScope(Dispatchers.Default).launch {
        repeat(10) {
            arrayChannel.send(it)
            println("$arrayChannel send $it")
        }
    }
    delay(100)
    arrayChannel.receive()
    arrayChannel.receive()
    delay(100)

    // 송신자가 무한정 송신할 수 있지만, 수신자가 channel 에 전송된 데이터를 유실 해도 괜찮은 경우 사용
    // 실제 현업에서 사용할일은 없을 듯
    val conflatedChannel = Channel<Int>(Channel.CONFLATED)
    CoroutineScope(Dispatchers.Default).launch {
        repeat(10) {
            conflatedChannel.send(it)
            println("$conflatedChannel send $it")
        }
    }
    delay(500)
    val element = conflatedChannel.receive()
    println("$conflatedChannel last is $element")
}