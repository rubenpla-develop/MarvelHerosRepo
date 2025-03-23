package com.rpla.marvelherosrepo.profile.ui.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.rpla.marvelherosrepo.R
import com.rpla.marvelherosrepo.profile.domain.entity.CharacterComicListEntity
import com.rpla.marvelherosrepo.profile.domain.entity.ComicsListResultsEntity

@Composable
fun ComicListBlock(modifier: Modifier, comics: CharacterComicListEntity) {
    Column(modifier = modifier.testTag("ComicListBlock")
        .wrapContentHeight()) {
        comics.comicsListEntities.forEach { comic ->
            ComicItem(comic)
        }
    }
}

@Composable
fun ComicItem(comic: ComicsListResultsEntity) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
        border = BorderStroke(2.dp, Color.DarkGray),
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(10.dp))
            .wrapContentHeight()
            .fillMaxWidth()
    ) {
        ConstraintLayout {
            val (infoBlock, photo) = createRefs()

            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current).data(data = comic.thumbnail)
                        .apply(block = fun ImageRequest.Builder.() {
                            placeholder(R.drawable.marvel_heroes_item_placeholder)
                            error(R.drawable.broken_image_placeholder)
                        }).build()
                ),
                contentDescription = "Avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(100.dp)
                    .width(50.dp)
                    .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
                    .constrainAs(photo) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    }
            )

            Column(modifier = Modifier
                .background(color = Color.Transparent)
                .fillMaxWidth()
                .padding(start = 8.dp, top = 8.dp)
                .testTag("ComicListInfo")
                .constrainAs(infoBlock) {
                    top.linkTo(photo.top)
                    start.linkTo(photo.end)
                }
            ) {
                Text(
                    text = stringResource(R.string.comic_item_title).plus(comic.name),
                    color = Color.White,
                    fontSize = 10.sp,
                    modifier = Modifier
                        .background(Color.DarkGray, RoundedCornerShape(4.dp))
                        .border(1.dp, Color.LightGray, RoundedCornerShape(4.dp))
                        .padding(top = 1.dp, start = 3.dp, bottom = 1.dp, end = 3.dp)
                )
                Text(
                    text = stringResource(R.string.comic_item_description).plus(comic.description),
                    color = Color.White,
                    fontSize = 10.sp,
                    maxLines = 2,
                    modifier = Modifier
                        .background(Color.DarkGray, RoundedCornerShape(4.dp))
                        .border(1.dp, Color.LightGray, RoundedCornerShape(4.dp))
                        .padding(top = 1.dp, start = 3.dp, bottom = 1.dp, end = 3.dp)
                )
                Text(
                    text = stringResource(R.string.comic_item_modified).plus(comic.modified),
                    color = Color.White,
                    fontSize = 10.sp,
                    modifier = Modifier
                        .background(Color.DarkGray, RoundedCornerShape(4.dp))
                        .border(1.dp, Color.LightGray, RoundedCornerShape(4.dp))
                        .padding(top = 1.dp, start = 3.dp, bottom = 1.dp, end = 3.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ComicItemPreview() {
    ComicListBlock(Modifier, CharacterComicListEntity(comicsList))
}

val comicsList = listOf(ComicsListResultsEntity(
    id = 1,
    name = "Comic Title",
    description = "Comic Description",
    modified = "2021-01-01",
    thumbnail = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784"
),
    ComicsListResultsEntity(
        id = 1,
        name = "Comic Title",
        description = "Comic Description",
        modified = "2021-01-01",
        thumbnail = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784"
    )
)