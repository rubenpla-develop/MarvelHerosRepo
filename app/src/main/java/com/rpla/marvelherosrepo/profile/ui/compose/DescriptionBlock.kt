package com.rpla.marvelherosrepo.profile.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.OutlinedFlag
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rpla.marvelherosrepo.R
import com.rpla.marvelherosrepo.profile.domain.entity.CharacterDetailEntity
import com.rpla.marvelherosrepo.ui.theme.Black
import com.rpla.marvelherosrepo.ui.theme.PinkA400

@Composable
fun DescriptionBlock(characterDetail: CharacterDetailEntity?) {
    val shouldShowMore = remember {
        mutableStateOf(DescriptionStatus.DEFAULT)
    }
    val maxLines = remember {
        mutableStateOf(4)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .wrapContentSize()
        ) {
            Icon(
                modifier = Modifier.padding(start = 8.dp),
                imageVector = Icons.Filled.OutlinedFlag,
                contentDescription = "Description label Icon",
                tint = PinkA400
            )
            Text(
                text = LocalContext.current.getString(R.string.description_title),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Black
            )
        }
        Text(
            modifier = Modifier.padding(8.dp),
            text = characterDetail?.description ?: "",
            textAlign = TextAlign.Start,
            overflow = TextOverflow.Ellipsis,
            maxLines = maxLines.value,
            color = Black,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            onTextLayout = {
                if (it.lineCount == 1 && it.isLineEllipsized(3)) {
                    shouldShowMore.value = DescriptionStatus.SHOW_MORE
                } else if (it.lineCount > 1) {
                    shouldShowMore.value = DescriptionStatus.SHOW_LESS
                }
            }
        )

        when (shouldShowMore.value) {
            DescriptionStatus.SHOW_MORE -> {
                Text(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp)
                        .clickable {
                            maxLines.value = Int.MAX_VALUE
                        },
                    text = LocalContext.current.getString(R.string.description_show_more),
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    textDecoration = TextDecoration.Underline,
                    color = PinkA400
                )
            }

            DescriptionStatus.SHOW_LESS -> {
                Text(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp)
                        .clickable {
                            maxLines.value = 4
                        },
                    text = LocalContext.current.getString(R.string.description_show_less),
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    textDecoration = TextDecoration.Underline,
                    color = PinkA400
                )
            }

            else -> {
                //do nothing
            }
        }
    }
}