package jp.matsuura.qiitasearchandroidapp.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TagItem(
    modifier: Modifier = Modifier,
    tagName: String,
) {
    Text(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = RoundedCornerShape(size = 8.dp),
            )
            .padding(8.dp),
        text = tagName,
        fontSize = 12.sp,
    )
}

@Preview(showBackground = true)
@Composable
private fun TagItemPreview() {
    TagItem(tagName = "Android")
}