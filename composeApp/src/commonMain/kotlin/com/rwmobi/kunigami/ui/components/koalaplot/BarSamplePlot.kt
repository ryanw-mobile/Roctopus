/*
 * Copyright (c) 2024. Ryan Wong
 * https://github.com/ryanw-mobile
 * Sponsored by RW MobiMedia UK Limited
 *
 */

@file:OptIn(ExperimentalMaterial3Api::class)

package com.rwmobi.kunigami.ui.components.koalaplot

import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RichTooltip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.rwmobi.kunigami.ui.theme.getDimension
import io.github.koalaplot.core.ChartLayout
import io.github.koalaplot.core.bar.DefaultVerticalBar
import io.github.koalaplot.core.bar.VerticalBarPlot
import io.github.koalaplot.core.bar.VerticalBarPlotEntry
import io.github.koalaplot.core.util.ExperimentalKoalaPlotApi
import io.github.koalaplot.core.util.VerticalRotation
import io.github.koalaplot.core.util.rotateVertically
import io.github.koalaplot.core.util.toString
import io.github.koalaplot.core.xygraph.DoubleLinearAxisModel
import io.github.koalaplot.core.xygraph.IntLinearAxisModel
import io.github.koalaplot.core.xygraph.TickPosition
import io.github.koalaplot.core.xygraph.XYGraph
import io.github.koalaplot.core.xygraph.rememberAxisStyle

@OptIn(ExperimentalKoalaPlotApi::class)
@Composable
fun BarSamplePlot(
    modifier: Modifier,
    entries: List<VerticalBarPlotEntry<Int, Double>>,
    yAxisRange: ClosedFloatingPointRange<Double>,
    yAxisTickPosition: TickPosition,
    xAxisTickPosition: TickPosition,
    title: String? = null,
    xAxisTitle: String? = null,
    yAxisTitle: String? = null,
    barWidth: Float,
) {
    val dimension = LocalDensity.current.getDimension()
    val barChartEntries = remember { entries }
    val colorPalette = remember {
        generateGYRHueColorPalette(
            saturation = 0.6f,
            lightness = 0.6f,
        )
    }

    ChartLayout(
        modifier = modifier,
        title = {
            title?.let {
                ChartTitle(title = title)
            }
        },
    ) {
        XYGraph(
            xAxisModel = IntLinearAxisModel(
                range = 0..entries.count() + 1,
                minimumMajorTickIncrement = 1,
                minimumMajorTickSpacing = 10.dp,
                zoomRangeLimit = 3,
                minorTickCount = entries.count() + 1,
            ),
            yAxisModel = DoubleLinearAxisModel(
                range = yAxisRange,
                minimumMajorTickIncrement = 0.1,
                minorTickCount = 5,
            ),
            xAxisStyle = rememberAxisStyle(
                tickPosition = xAxisTickPosition,
                color = Color.LightGray,
            ),
            xAxisLabels = {
                if (it % 5 == 0) {
                    AxisLabel(
                        modifier = Modifier.padding(top = 2.dp),
                        label = "$it",
                    )
                }
            },
            xAxisTitle = {
                xAxisTitle?.let {
                    AxisTitle(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = dimension.grid_1),
                        title = it,
                    )
                }
            },
            yAxisStyle = rememberAxisStyle(tickPosition = yAxisTickPosition),
            yAxisLabels = {
                AxisLabel(
                    modifier = Modifier.absolutePadding(right = 2.dp),
                    label = it.toString(1),
                )
            },
            yAxisTitle = {
                yAxisTitle?.let {
                    AxisTitle(
                        modifier = Modifier
                            .rotateVertically(VerticalRotation.COUNTER_CLOCKWISE)
                            .padding(bottom = dimension.grid_1),
                        title = it,
                    )
                }
            },
            verticalMajorGridLineStyle = null,
        ) {
            VerticalBarPlot(
                data = barChartEntries,
                bar = { index ->
                    DefaultVerticalBar(
                        brush = SolidColor(
                            colorPalette[
                                getPercentageColorIndex(
                                    value = barChartEntries[index].y.yMax,
                                    maxValue = yAxisRange.endInclusive,
                                ),
                            ],
                        ),
                        modifier = Modifier.fillMaxWidth()
//                            .pointerInput(Unit) {
//                                detectTapGestures(
//                                    onPress = { /* Called when the press is detected */ },
//                                    onDoubleTap = { /* Called on double tap */ },
//                                    onLongPress = { /* Called on long press */ },
//                                    onTap = {
//
//                                        // Handle the tap
//                                        if (!thumbnail) {
//                                            HoverSurface { Text(barChartEntries[index].y.yMax.toString()) }
//                                        }
//                                    },
//                                )
//                            }
                            .hoverableElement {
                                //   HoverSurface(padding = dimension.grid_1) {
                                RichTooltip {
                                    Text(
                                        text = barChartEntries[index].y.yMax.toString(),
                                    )
                                }
                                //   }
                            },
                    )
                },
                barWidth = barWidth,
            )
        }
    }
}

private fun getPercentageColorIndex(value: Double, maxValue: Double): Int = ((value / maxValue) * 100).toInt()

private fun generateGYRHueColorPalette(
    saturation: Float = 0.5f,
    lightness: Float = 0.5f,
): List<Color> {
    val count = 100
    val startHue = 120f // Starting at green
    val endHue = 0f // Ending at red
    val delta = (endHue - startHue) / (count - 1) // Calculate delta for exactly 100 steps

    return List(count) { i ->
        val hue = startHue + delta * i // Compute the hue for this index
        Color.hsl(hue, saturation, lightness)
    }
}