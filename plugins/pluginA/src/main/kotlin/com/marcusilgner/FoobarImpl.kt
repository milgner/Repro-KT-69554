package com.marcusilgner

import arrow.core.NonEmptySet
import org.pf4j.Extension

@Extension
class FoobarImpl : Foobar {
    override fun <T: Any> invoke(aggregateFunctions: NonEmptySet<T>) {
        aggregateFunctions.forEach { aggregateFunction -> println(aggregateFunction) }
    }
}