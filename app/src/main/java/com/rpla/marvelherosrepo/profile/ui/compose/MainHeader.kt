package com.rpla.marvelherosrepo.profile.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person2
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.rpla.marvelherosrepo.R
import com.rpla.marvelherosrepo.profile.domain.entity.CharacterDetailEntity
import com.rpla.marvelherosrepo.ui.theme.Black
import com.rpla.marvelherosrepo.ui.theme.PinkA400

@Composable
fun MainHeader(characterDetail: CharacterDetailEntity?) {
    ConstraintLayout {
        val (avatarIcon, characterImage) = createRefs()
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .graphicsLayer {
                    clip = true
                    shape = BottomRoundedArcShape()
                }
                .constrainAs(characterImage) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            contentScale = ContentScale.Crop,
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current).data(data = characterDetail?.thumbnail)
                    .apply<ImageRequest.Builder>(block = fun ImageRequest.Builder.() {
                        placeholder(R.drawable.marvel_heroes_item_placeholder)
                        error(R.drawable.broken_image_placeholder)
                        crossfade(true)
                    }).build()
            ),
            contentDescription = "Character Image")

        AvatarIcon(
            modifier = Modifier.constrainAs(avatarIcon) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
    }
    characterDetail?.let {
        Text(
        modifier = Modifier.padding(start = 16.dp, top = 30.dp, end = 16.dp),
        text = it.name,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        color = Black
    )
    }
}

@Composable
fun AvatarIcon(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .offset(0.dp, 25.dp)
    ) {
        Icon(
            modifier = Modifier.size(48.dp),
            imageVector = Icons.Filled.Person2,
            contentDescription = "Avatar Icon",
            tint = PinkA400
        )
    }
}