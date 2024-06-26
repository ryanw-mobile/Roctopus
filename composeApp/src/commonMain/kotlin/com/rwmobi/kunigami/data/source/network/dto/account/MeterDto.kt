/*
 * Copyright (c) 2024. Ryan Wong
 * https://github.com/ryanw-mobile
 * Sponsored by RW MobiMedia UK Limited
 *
 */

package com.rwmobi.kunigami.data.source.network.dto.account

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MeterDto(
    @SerialName("serial_number") val serialNumber: String,
    @SerialName("registers") val registers: List<RegisterDto>? = null,
)
