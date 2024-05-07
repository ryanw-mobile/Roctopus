/*
 * Copyright (c) 2024. Ryan Wong
 * https://github.com/ryanw-mobile
 * Sponsored by RW MobiMedia UK Limited
 *
 */

package com.rwmobi.roctopus.data.source.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class StandardUnitRatesApiResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<RateDto>,
)
