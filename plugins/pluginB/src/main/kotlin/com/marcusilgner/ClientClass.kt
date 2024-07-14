package com.marcusilgner

import arrow.core.nonEmptySetOf

class ClientClass(val statistics: Foobar) {
    fun doThings() {
        statistics.invoke(nonEmptySetOf("A", "B", "C"))
    }
}
