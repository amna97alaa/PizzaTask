package com.amna.pizzatask.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.amna.pizzatask.ui.theme.Black_87

@Composable
fun ButtonIcon(modifier: Modifier = Modifier, onBack: () -> Unit = {}, painter: Painter) {
    IconButton(
        onClick = onBack,
        modifier = modifier.size(32.dp)

    ) {
        Icon(
            painter = painter,
            contentDescription = null, tint = Black_87
        )
    }
}