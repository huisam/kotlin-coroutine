package com.huisam.kotlincoroutine.arrow

import arrow.core.curried

// f(x, y, z)
private val strong: (String, String, String) -> String = { style, id, body ->
    "<string id=\"$id\" style=\"$style\">$body</strong>"
}

fun main() {
    // f(a(x), b(y), c(z))
    val curriedStrong: (String) -> (String) -> (String) -> String = strong.curried()

    // f(상수, b(y), c(z))
    val greenStrong: (String) -> (String) -> String = curriedStrong("color: green")

    println("<string id=\"red_id\" style=\"color: red\">content</strong>" == curriedStrong("color: red")("red_id")("content"))

    println("<string id=\"green_id\" style=\"color: green\">content</strong>" == greenStrong("green_id")("content"))
}