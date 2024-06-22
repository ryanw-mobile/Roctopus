/*
 * Copyright (c) 2024. Ryan Wong
 * https://github.com/ryanw-mobile
 * Sponsored by RW MobiMedia UK Limited
 *
 */

package com.rwmobi.kunigami.di

import com.rwmobi.kunigami.domain.usecase.GenerateUsageInsightsUseCase
import com.rwmobi.kunigami.ui.viewmodels.AccountViewModel
import com.rwmobi.kunigami.ui.viewmodels.AgileViewModel
import com.rwmobi.kunigami.ui.viewmodels.TariffsViewModel
import com.rwmobi.kunigami.ui.viewmodels.UsageViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelModule = module {
    factory {
        UsageViewModel(
            syncUserProfileUseCase = get(),
            getConsumptionAndCostUseCase = get(),
            getTariffUseCase = get(),
            generateUsageInsightsUseCase = GenerateUsageInsightsUseCase(),
            dispatcher = get(named("DefaultDispatcher")),
        )
    }

    factory {
        AgileViewModel(
            getTariffUseCase = get(),
            syncUserProfileUseCase = get(),
            getTariffRatesUseCase = get(),
            getStandardUnitRateUseCase = get(),
            dispatcher = get(named("DefaultDispatcher")),
        )
    }

    factory {
        TariffsViewModel(
            restApiRepository = get(),
            getFilteredProductsUseCase = get(),
            dispatcher = get(named("DefaultDispatcher")),
        )
    }

    factory {
        AccountViewModel(
            userPreferencesRepository = get(),
            getTariffUseCase = get(),
            initialiseAccountUseCase = get(),
            updateMeterPreferenceUseCase = get(),
            syncUserProfileUseCase = get(),
            dispatcher = get(named("DefaultDispatcher")),
        )
    }
}
