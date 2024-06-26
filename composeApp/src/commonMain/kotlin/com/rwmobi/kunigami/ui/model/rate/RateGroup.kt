/*
 * Copyright (c) 2024. Ryan Wong
 * https://github.com/ryanw-mobile
 * Sponsored by RW MobiMedia UK Limited
 *
 */

package com.rwmobi.kunigami.ui.model.rate

import androidx.compose.runtime.Immutable
import com.rwmobi.kunigami.domain.model.rate.Rate
import kotlinx.datetime.Instant
import kotlin.time.Duration

@Immutable
data class RateGroup(
    val title: String,
    val rates: List<Rate>,
)

fun List<RateGroup>.findActiveRate(referencePoint: Instant): Rate? {
    this.forEach { group ->
        val activeRate = group.rates.find { rate ->
            rate.isActive(referencePoint)
        }
        if (activeRate != null) {
            return activeRate
        }
    }
    return null
}

fun List<RateGroup>.getRateTrend(activeRate: Rate?): RateTrend? {
    return activeRate?.let {
        if (it.validity.endInclusive == Instant.DISTANT_FUTURE) {
            RateTrend.STEADY
        } else {
            val nextRate = findActiveRate(referencePoint = it.validity.endInclusive.plus(Duration.parse("5m")))

            when {
                nextRate == null -> null
                it.vatInclusivePrice > nextRate.vatInclusivePrice -> RateTrend.DOWN
                it.vatInclusivePrice == nextRate.vatInclusivePrice -> RateTrend.STEADY
                else -> RateTrend.UP
            }
        }
    }
}
