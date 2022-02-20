package com.huisam.kotlincoroutine.chapter5

/**
 * sequence 는 상태가 없어서 특정 element 꺼내오려면 처음부터 yield 를 다시 진행한다
 */
fun main() {
    val sequence = sequence {
        for (i in 0..9) {
            println("yielding $i")
            yield(i)
        }
    }
    sequence.elementAtOrNull(1)

    sequence.take(3).joinToString()
}