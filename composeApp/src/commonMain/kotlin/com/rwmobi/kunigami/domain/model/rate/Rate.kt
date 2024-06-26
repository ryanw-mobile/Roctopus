/*
 * Copyright (c) 2024. Ryan Wong
 * https://github.com/ryanw-mobile
 * Sponsored by RW MobiMedia UK Limited
 *
 */

package com.rwmobi.kunigami.domain.model.rate

import androidx.compose.runtime.Immutable
import kotlinx.datetime.Instant

@Immutable
data class Rate(
    val vatInclusivePrice: Double,
    val validity: ClosedRange<Instant>, // Caller should end using Instant.DISTANT_FUTURE if API returns null
    val paymentMethod: PaymentMethod,
) {
    fun isActive(referencePoint: Instant): Boolean {
        return validity.contains(referencePoint)
    }
}
