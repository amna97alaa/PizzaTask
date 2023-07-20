package com.amna.pizzatask.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.amna.pizzatask.R
import com.amna.pizzatask.ui.theme.Black_87
import com.amna.pizzatask.ui.theme.Typography

@Preview(showSystemUi = true)
@Composable
fun AppBar( modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        ButtonIcon(onBack = {}, painter = painterResource(id = R.drawable.ic_back ))
        Text(text ="Pizza", color = Black_87, style = Typography.titleLarge)
        ButtonIcon(onBack = {}, painter = painterResource(id = R.drawable.ic_heart ))
    }
}