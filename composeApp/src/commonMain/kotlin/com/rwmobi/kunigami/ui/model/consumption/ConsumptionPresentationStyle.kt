/*
 * Copyright (c) 2024. Ryan Wong
 * https://github.com/ryanw-mobile
 * Sponsored by RW MobiMedia UK Limited
 *
 */

package com.rwmobi.kunigami.ui.model.consumption

import com.rwmobi.kunigami.domain.model.consumption.ConsumptionTimeFrame
import kunigami.composeapp.generated.resources.Res
import kunigami.composeapp.generated.resources.presentation_style_day_half_hourly
import kunigami.composeapp.generated.resources.presentation_style_month_thirty_days
import kunigami.composeapp.generated.resources.presentation_style_month_weeks
import kunigami.composeapp.generated.resources.presentation_style_week_seven_days
import kunigami.composeapp.generated.resources.presentation_style_year_twelve_months
import org.jetbrains.compose.resources.StringResource

enum class ConsumptionPresentationStyle(val stringResource: StringResource) {
    DAY_HALF_HOURLY(stringResource = Res.string.presentation_style_day_half_hourly),
    WEEK_SEVEN_DAYS(stringResource = Res.string.presentation_style_week_seven_days),
    MONTH_WEEKS(stringResource = Res.string.presentation_style_month_weeks),
    MONTH_THIRTY_DAYS(stringResource = Res.string.presentation_style_month_thirty_days),
    YEAR_TWELVE_MONTHS(stringResource = Res.string.presentation_style_year_twelve_months),
    ;

    /***
     * UI uses interprets the grouping differently. This is to map to the Enum that the API expects.
     */
    fun getConsumptionDataGroup(): ConsumptionTimeFrame {
        return when (this) {
            DAY_HALF_HOURLY -> ConsumptionTimeFrame.HALF_HOURLY
            WEEK_SEVEN_DAYS -> ConsumptionTimeFrame.DAY
            MONTH_WEEKS -> ConsumptionTimeFrame.WEEK
            MONTH_THIRTY_DAYS -> ConsumptionTimeFrame.DAY
            YEAR_TWELVE_MONTHS -> ConsumptionTimeFrame.MONTH
        }
    }
}
