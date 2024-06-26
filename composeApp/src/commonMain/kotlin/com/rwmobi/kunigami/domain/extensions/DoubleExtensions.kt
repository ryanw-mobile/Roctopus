/*
 * Copyright (c) 2024. Ryan Wong
 * https://github.com/ryanw-mobile
 * Sponsored by RW MobiMedia UK Limited
 *
 */

package com.rwmobi.kunigami.domain.extensions

import kotlin.math.round
import kotlin.math.roundToLong

/**
 * Electricity consumption data is returned to the nearest 0.001kwh.
 * For billing, consumption is rounded to the nearest 0.01kwh before multiplying by the price.
 * The rounding method used is rounding half to even, where numbers ending in 5 are rounded up or down, towards the nearest even hundredth decimal place.
 * As a result, 0.015 would be rounded up to 0.02, while 0.025 is rounded down to 0.02.
 */
fun Double.roundToNearestEvenHundredth(): Double {
    val scaled = this * 100
    val rounded = if (scaled % 1.0 == 0.5) {
        if (scaled.toLong() % 2 == 0L) {
            scaled.toLong() // It's already even
        } else {
            scaled.toLong() + 1 // Round up to the nearest even
        }
    } else {
        scaled.roundToLong()
    }
    return rounded / 100.0
}

fun Double.roundToTwoDecimalPlaces(): Double {
    return round(this * 100) / 100
}
