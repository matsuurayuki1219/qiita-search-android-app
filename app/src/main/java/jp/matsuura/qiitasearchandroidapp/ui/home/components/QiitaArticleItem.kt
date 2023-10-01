package jp.matsuura.qiitasearchandroidapp.ui.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import jp.matsuura.qiitasearchandroidapp.R
import jp.matsuura.qiitasearchandroidapp.ext.toAppString
import jp.matsuura.qiitasearchandroidapp.model.QiitaArticleModel
import jp.matsuura.qiitasearchandroidapp.model.TagModel
import jp.matsuura.qiitasearchandroidapp.model.UserModel
import java.time.OffsetDateTime

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun QiitaArticleItem(
    modifier: Modifier = Modifier,
    qiitaItem: QiitaArticleModel,
    onClick: (url: String) -> Unit,
) {
    Card(
        modifier = modifier
            .padding(16.dp)
            .clip(MaterialTheme.shapes.medium)
            .clickable {
                onClick(qiitaItem.url)
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {

                AsyncImage(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape),
                    model = qiitaItem.user.profileImageUrl,
                    contentDescription = null,
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = "@${qiitaItem.user.id}",
                        fontSize = 14.sp,
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = qiitaItem.createdAt.toAppString(),
                        fontSize = 14.sp,
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = qiitaItem.title,
                fontSize = 18.sp,
            )

            Spacer(modifier = Modifier.height(12.dp))

            if (qiitaItem.tags.isNotEmpty()) {
                FlowRow {
                    qiitaItem.tags.forEach {
                        TagItem(
                            modifier = Modifier.padding(end = 8.dp, bottom = 8.dp),
                            tagName = it.name
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_favorite),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary,
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = qiitaItem.likesCount.toString(),
                    fontSize = 16.sp,
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun QiitaArticleItemPreview() {
    QiitaArticleItem(
        qiitaItem = QiitaArticleModel(
            id = "1",
            title = "Jetpack Composeの基本について",
            createdAt = OffsetDateTime.now(),
            updatedAt = OffsetDateTime.now(),
            likesCount = 10,
            tags = listOf(
                TagModel(
                    name = "iOS",
                ),
                TagModel(
                    name = "Android",
                ),
                TagModel(
                    name = "MVVM",
                ),
            ),
            url = "",
            user = UserModel(
                id = "1",
                name = "matsuurayuki",
                profileImageUrl = "",
            )
        ),
        onClick = {},
    )
}