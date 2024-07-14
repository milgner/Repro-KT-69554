package com.marcusilgner

import arrow.core.nonEmptySetOf

class ClientClass(val statistics: Statistics) {
    fun doThings() {
        statistics.invoke(nonEmptySetOf(NumericAggregates.Min, NumericAggregates.Max))
    }
}