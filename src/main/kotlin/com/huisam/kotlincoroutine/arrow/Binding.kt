package com.huisam.kotlincoroutine.arrow

import arrow.core.partially1 as bind

// f(x)
private val footer: (String) -> String = { "<footer>$it</footer>" }

fun main() {
    // g()
    val fixFooter: () -> String = footer.bind("Functional Kotlin")

    // g() -> f(상수)
    println("<footer>Functional Kotlin</footer>" == fixFooter())
}