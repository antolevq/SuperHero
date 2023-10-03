package it.leva.superhero.ui.components

import android.text.SpannableStringBuilder
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun HeroLabel(
    modifier: Modifier = Modifier,
    text: String,
    labelType: LabelType,
    textAlign: TextAlign? = null
) {
    Text(
        modifier = modifier,
        text = text,
        style = labelType.getTextStyle(),
        textAlign = textAlign
    )
}

enum class LabelType {
    NORMAL,
    BIG,
    SMALL;

    fun getTextStyle() =
        when (this) {
            NORMAL -> TextStyle(fontSize = 18.sp)
            BIG -> TextStyle(fontSize = 22.sp)
            SMALL -> TextStyle(fontSize = 14.sp)
        }

}