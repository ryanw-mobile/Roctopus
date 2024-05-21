/*
 * Copyright (c) 2024. Ryan Wong
 * https://github.com/ryanw-mobile
 * Sponsored by RW MobiMedia UK Limited
 *
 */

package com.rwmobi.kunigami.ui.model

import androidx.compose.runtime.Immutable
import com.rwmobi.kunigami.domain.model.Consumption

@Immutable
data class ConsumptionGroup(
    val title: String,
    val consumptions: List<Consumption>,
)