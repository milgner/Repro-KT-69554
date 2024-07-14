package com.marcusilgner

import arrow.core.NonEmptySet
import org.pf4j.Extension

@Extension
class StatisticsImpl : Statistics {
    override fun <AT, AF : AggregateFunction<AT>> invoke(aggregateFunctions: NonEmptySet<AF>): AggregatedValues<AF, AT> {
        aggregateFunctions.forEach { aggregateFunction -> println(aggregateFunction) }

        return AggregatedValues(emptyMap(), 0.0)
    }
}