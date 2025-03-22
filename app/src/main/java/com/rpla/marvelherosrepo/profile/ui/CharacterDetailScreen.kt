package com.rpla.marvelherosrepo.profile.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.rpla.marvelherosrepo.profile.domain.entity.CharacterDetailEntity
import com.rpla.marvelherosrepo.profile.ui.compose.MainHeader
import com.rpla.marvelherosrepo.profile.ui.compose.DescriptionBlock
import com.rpla.marvelherosrepo.profile.ui.viewmodel.CharacterDetailIntent
import com.rpla.marvelherosrepo.profile.ui.viewmodel.CharacterDetailState
import com.rpla.marvelherosrepo.profile.ui.viewmodel.CharacterDetailViewModel
import com.rpla.marvelherosrepo.ui.navigation.DEFAULT_CHARACTER_ID
import com.rpla.marvelherosrepo.ui.theme.PinkA400
import com.rpla.marvelherosrepo.ui.theme.White

@Composable
fun CharacterDetailScreen(
    characterId: Int? = DEFAULT_CHARACTER_ID,
    viewModel: CharacterDetailViewModel = hiltViewModel(),
    onBackButtonPressed: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.setCharacterId(characterId!!)
        viewModel.dispatchIntent(CharacterDetailIntent.CharacterDetail)
    }

    val uiState = viewModel.state.collectAsState()

    when (uiState.value) {
        is CharacterDetailState.InitialState -> {
            Log.i("CharacterDetail", "InitialState")
        }

        is CharacterDetailState.LoadingState -> {
            Log.i("CharacterDetail", "LoadingState")
        }

        is CharacterDetailState.CharacterDetailData -> {
            Log.i("CharacterDetail", "Get character detail State")
            val characterDetail =
                (uiState.value as CharacterDetailState.CharacterDetailData).characterDetail

            CharacterDetails(characterDetail, onBackButtonPressed)

        }
        else -> {}
    }
}

@Composable
fun CharacterDetails(
    characterDetail: CharacterDetailEntity?,
    onBackButtonPressed: () -> Unit
) {
    val scrollState = rememberScrollState()

    ConstraintLayout {
        val (characterDetailsView, backButton) = createRefs()

        Column(
            modifier = Modifier
                .background(color = Color.White)
                .fillMaxSize()
                .verticalScroll(scrollState)
                .testTag("CharacterDetailsParent")
                .constrainAs(characterDetailsView) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            MainHeader(characterDetail)
            DescriptionBlock(characterDetail)
        }

        IconButton(onClick = { onBackButtonPressed() },
            modifier = Modifier
                .background(PinkA400, shape = CircleShape)
                .border(1.dp, White, shape = CircleShape)
                .size(48.dp)
                .constrainAs(backButton) {
                    start.linkTo(parent.start, margin = 16.dp)
                    top.linkTo(parent.top, margin = 16.dp)
                }) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back Button",
                tint = White
            )
        }
    }

}


@Preview(showBackground = true)
@Composable
fun CharacterDetailPreview() {
    CharacterDetails(
        CharacterDetailEntity(
            1011334,
        "Template Hero",
        MEDIUM_LOREM_IPSUM,
        "http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16/landscape_xlarge.jpg",
        )
    ) {}
}

const val MEDIUM_LOREM_IPSUM =
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Volutpat odio facilisis mauris sit. Amet venenatis urna cursus eget nunc scelerisque viverra mauris. Massa tempor nec feugiat nisl. Convallis a cras semper auctor neque. Lectus sit amet est placerat in. Adipiscing tristique risus nec feugiat."
const val HUGE_LOREM_IPSUM =
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Amet cursus sit amet dictum sit amet justo donec enim. Consectetur libero id faucibus nisl tincidunt eget nullam non. Lorem donec massa sapien faucibus et molestie ac feugiat sed. Tellus in metus vulputate eu. Lacus sed viverra tellus in hac habitasse platea dictumst. Duis ut diam quam nulla. Rhoncus urna neque viverra justo nec. Eget nunc lobortis mattis aliquam faucibus purus in. Nunc id cursus metus aliquam eleifend mi in nulla. Blandit turpis cursus in hac habitasse platea dictumst. Enim eu turpis egestas pretium aenean pharetra. Velit egestas dui id ornare arcu odio. Vitae congue mauris rhoncus aenean vel elit scelerisque mauris pellentesque. Velit laoreet id donec ultrices tincidunt arcu. Tellus integer feugiat scelerisque varius morbi enim nunc. Sit amet justo donec enim. Lectus quam id leo in vitae turpis massa sed elementum. Molestie a iaculis at erat pellentesque adipiscing commodo. Felis eget velit aliquet sagittis id."