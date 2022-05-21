package com.huisam.kotlincoroutine.arrow

import arrow.core.andThen
import arrow.core.compose

// f(x)
private val span: (String) -> String = { "<span>$it</span>" }

// g(x)
private val div: (String) -> String = { "<div>$it</div>" }

fun main() {
    // g(f(x)) = g(x) o f(x)
    val spanAndDiv: (String) -> String = span andThen div

    // f(g(x)) = f(x) o g(x)
    val divAndSpan: (String) -> String = span compose div

    println("<div><span>test</span></div>" == spanAndDiv("test"))
    println("<span><div>test</div></span>" == divAndSpan("test"))
}