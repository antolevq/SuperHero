package it.leva.superhero.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.leva.superhero.R
import it.leva.superhero.ui.components.HeroLabel
import it.leva.superhero.ui.components.LabelType


@Composable
fun HeroErrorScreen(errorMessage: String = "test") {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.width(60.dp),
            painter = painterResource(id = R.drawable.ops_icon),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(8.dp))
        HeroLabel(
            modifier = Modifier.align(CenterHorizontally),
            text = errorMessage,
            labelType = LabelType.BIG
        )
    }
}

