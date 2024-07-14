package com.marcusilgner

import arrow.core.NonEmptySet
import org.pf4j.ExtensionPoint

interface Foobar : ExtensionPoint {
    operator fun <T : Any> invoke(
        /* leaving out irrelevant params */
        aggregateFunctions: NonEmptySet<T>,
    )
}