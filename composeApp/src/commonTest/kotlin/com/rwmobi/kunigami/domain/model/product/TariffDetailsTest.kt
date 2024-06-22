/*
 * Copyright (c) 2024. Ryan Wong
 * https://github.com/ryanw-mobile
 * Sponsored by RW MobiMedia UK Limited
 *
 */

package com.rwmobi.kunigami.domain.model.product

import kotlinx.datetime.Instant
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TariffDetailsTest {
    private val tariffCode = "E-1R-GO-GREEN-VAR-22-10-14-A"
    private val sampleTariff = Tariff(
        tariffPaymentTerm = TariffPaymentTerm.DIRECT_DEBIT_MONTHLY,
        tariffCode = tariffCode,
        vatInclusiveStandingCharge = 100.0,
        vatInclusiveOnlineDiscount = 5.0,
        vatInclusiveDualFuelDiscount = 10.0,
        exitFeesType = ExitFeesType.NONE,
        vatInclusiveExitFees = 0.0,
        vatInclusiveStandardUnitRate = 20.0,
        vatInclusiveDayUnitRate = null,
        vatInclusiveNightUnitRate = null,
        productCode = "GO-GREEN-VAR-22-10-14",
        fullName = "Octopus Go Green October 2022 v1",
        displayName = "Octopus Go Green",
        description = "Octopus Go Green is our EV tariff exclusively available to Volkswagen Group EV drivers.",
        isVariable = true,
        availability = Instant.parse("2022-10-14T00:00:00+01:00")..Instant.DISTANT_FUTURE,
        tariffActiveAt = Instant.parse("2024-06-22T16:54:27.330542Z"),
    )

    @Test
    fun `hasStandardUnitRate should return true when standard unit rate is not null`() {
        val tariff = sampleTariff
        assertTrue(tariff.hasStandardUnitRate())
    }

    @Test
    fun `hasStandardUnitRate should return false when standard unit rate is null`() {
        val tariff = sampleTariff.copy(
            vatInclusiveStandardUnitRate = null,
        )
        assertFalse(tariff.hasStandardUnitRate())
    }

    @Test
    fun `hasDualRates should return true when both day and night unit rates are not null`() {
        val tariff = sampleTariff.copy(
            vatInclusiveStandardUnitRate = null,
            vatInclusiveDayUnitRate = 20.0,
            vatInclusiveNightUnitRate = 15.0,
        )
        assertTrue(tariff.hasDualRates())
    }

    @Test
    fun `hasDualRates should return false when day unit rate is null`() {
        val tariff = sampleTariff.copy(
            vatInclusiveStandardUnitRate = 20.0,
            vatInclusiveDayUnitRate = null,
            vatInclusiveNightUnitRate = 15.0,
        )
        assertFalse(tariff.hasDualRates())
    }

    @Test
    fun `hasDualRates should return false when night unit rate is null`() {
        val tariff = sampleTariff.copy(
            vatInclusiveStandardUnitRate = 20.0,
            vatInclusiveDayUnitRate = 20.0,
            vatInclusiveNightUnitRate = null,
        )
        assertFalse(tariff.hasDualRates())
    }
}
