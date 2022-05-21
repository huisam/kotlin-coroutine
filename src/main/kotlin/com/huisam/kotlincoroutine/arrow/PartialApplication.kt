package com.huisam.kotlincoroutine.arrow

import arrow.core.partially2

// f(x,y,z)
private val strong: (String, String, String) -> String = { id, style, body ->
    "<string id=\"$id\" style=\"$style\">$body</strong>"
}

fun main() {
    // g(x, z)
    val redStrong: (String, String) -> String = strong.partially2("font: red")

    // g(x, z) -> f(x, 상수, z)
    println("<string id=\"red_id\" style=\"font: red\">content</strong>" == redStrong("red_id", "content"))
}