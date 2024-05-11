/*
 * Copyright (c) 2024. Ryan Wong
 * https://github.com/ryanw-mobile
 * Sponsored by RW MobiMedia UK Limited
 *
 */

@file:OptIn(ExperimentalLayoutApi::class, ExperimentalResourceApi::class)

package com.rwmobi.kunigame.ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import com.rwmobi.kunigame.domain.model.Product
import com.rwmobi.kunigame.domain.model.ProductDirection
import com.rwmobi.kunigame.ui.theme.AppTheme
import com.rwmobi.kunigame.ui.theme.getDimension
import kotlinx.datetime.Instant
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    product: Product,
) {
    val dimension = LocalDensity.current.getDimension()

    Column(
        modifier = modifier.padding(
            vertical = dimension.grid_1,
            horizontal = dimension.grid_2,
        ),
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Black,
            text = product.fullName,
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.bodyMedium,
            text = product.description,
        )

        FlowRow(modifier = Modifier.padding(vertical = dimension.grid_1)) {
            product.features.forEach {
                featureChip(
                    text = stringResource(resource = it.stringResource),
                )
            }
        }
    }
}

@Composable
private fun featureChip(
    modifier: Modifier = Modifier,
    text: String,
) {
    val dimension = LocalDensity.current.getDimension()

    Text(
        modifier = modifier
            .padding(horizontal = dimension.grid_0_25)
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = MaterialTheme.shapes.medium,
            )
            .padding(
                horizontal = dimension.grid_1,
                vertical = dimension.grid_0_5,
            ),
        color = MaterialTheme.colorScheme.onSecondaryContainer,
        style = MaterialTheme.typography.labelMedium,
        text = text.uppercase(),
    )
}

@Preview
@Composable
private fun ProductItemPreview() {
    AppTheme {
        ProductItem(
            modifier = Modifier.fillMaxWidth(),
            product = Product(
                code = "Annie",
                direction = ProductDirection.IMPORT,
                fullName = "Alfredo",
                displayName = "Stephone",
                description = "Tarryn",
                features = emptyList(),
                term = null,
                availableFrom = Instant.parse("2024-03-31T23:00:00Z"),
                availableTo = null,
                brand = "Phong",
            ),
        )
    }
}
