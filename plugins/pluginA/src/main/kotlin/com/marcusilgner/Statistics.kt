package com.marcusilgner

import arrow.core.NonEmptySet
import org.pf4j.ExtensionPoint

interface AggregateFunction<AT>

enum class NumericAggregates : AggregateFunction<Double> {
    Min,Max,Sum,Avg
}

data class AggregatedValues<AF : AggregateFunction<AT>, AT>(
    val results: Map<AF, AT?>,

    val coverage: Double = 0.0
) : Map<AF, AT?> by results

interface Statistics : ExtensionPoint {
    operator fun <AT, AF : AggregateFunction<AT>> invoke(
        /* leaving out irrelevant params */
        aggregateFunctions: NonEmptySet<AF>,
    ): AggregatedValues<AF, AT>
}