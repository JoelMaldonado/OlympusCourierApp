package com.atm.olympuscourierapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.atm.olympuscourierapp.ui.features.VerRepartos.Components.FiltroItem
import com.atm.olympuscourierapp.util.capitalizeWords


@Composable
fun <T> FiltroGenerico(
    title: String,
    value: T?,
    newValue: (T?) -> Unit,
    list: List<T>,
    labelProvider: (T) -> String
) {
    TextSelect(
        title = title
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FiltroItem(
                label = "Todos",
                isSelected = value == null,
                click = {
                    newValue(null)
                }
            )
            list.forEach { item ->
                FiltroItem(
                    label = labelProvider(item).capitalizeWords(),
                    isSelected = value == item,
                    click = {
                        newValue(item)
                    }
                )
            }
        }
    }
}