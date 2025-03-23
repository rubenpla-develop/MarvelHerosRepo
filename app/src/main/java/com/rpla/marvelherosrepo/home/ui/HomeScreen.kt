package com.rpla.marvelherosrepo.home.ui

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.rpla.marvelherosrepo.R
import com.rpla.marvelherosrepo.home.domain.entity.CharacterResultsEntity
import com.rpla.marvelherosrepo.home.ui.viewModel.CharactersListViewModel
import com.rpla.marvelherosrepo.home.ui.viewModel.HomeIntent
import com.rpla.marvelherosrepo.home.ui.viewModel.HomeState
import com.rpla.marvelherosrepo.ui.common.HomeAppBar
import com.rpla.marvelherosrepo.ui.common.LoadingItem

import com.rpla.marvelherosrepo.ui.navigation.Routes
import com.rpla.marvelherosrepo.ui.theme.PurpleGrey40

@Composable
fun HomeScreen(
    navigationController: NavHostController
) {
    Scaffold(topBar = {
        HomeAppBar(title = stringResource(R.string.app_name),
            modifier = Modifier,
            openFilters = {}
        )
    },
        content = { innerPadding ->
            WorkersGridList(
                paddingValues = innerPadding,
                navigationController = navigationController
            )
        })
}

@Composable
fun WorkersGridList(
    viewModel: CharactersListViewModel = hiltViewModel(),
    paddingValues: PaddingValues,
    navigationController: NavHostController
) {

    LaunchedEffect(Unit) {
        viewModel.dispatchIntent(HomeIntent.AllCharacters)
    }

    val uiState = viewModel.state.collectAsState()

    when (uiState.value) {
        is HomeState.InitialState -> { Log.d("HomeScreen", "InitialState") }
        is HomeState.LoadingState -> { Log.d("HomeScreen", "LoadingState") }
        is HomeState.CharactersListData -> {
            val characterItems =
                (uiState.value as HomeState.CharactersListData).characters.collectAsLazyPagingItems()
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(top = paddingValues.calculateTopPadding())
                    .background(color = PurpleGrey40),
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                items(characterItems.itemCount) { itemIndex ->
                    characterItems[itemIndex]?.let { characterEntity ->
                        CharacterItem(character = characterEntity) { id ->
                            navigationController.navigate(Routes.CharacterProfile.createRoute(id.toString()))
                        }
                    }
                }

                characterItems.apply {
                    when {
                        loadState.refresh is LoadState.Loading -> {
                            item { LoadingItem() }
                            item { LoadingItem() }
                        }

                        loadState.append is LoadState.Loading -> {
                            item { LoadingItem() }
                            item { LoadingItem() }
                        }

                        loadState.refresh is LoadState.Error -> {}
                        loadState.append is LoadState.Error -> {}
                    }
                }
            }
        }

        else -> {}
    }
}

@Composable
fun CharacterItem(
    character: CharacterResultsEntity,
    onCharacterClicked: (id: Int) -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
        border = BorderStroke(2.dp, Color.DarkGray),
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(10.dp))
            .height(200.dp)
            .fillMaxWidth()
            .clickable {
                Log.i("characterItem", "Character with id ${character.id} clicked")
                onCharacterClicked(character.id)
            }) {
        ConstraintLayout {

            val (name, comics, photo, spacerTop, spacerStart,
                spacerBottom, spacerEnd) = createRefs()

            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current).data(data =character.thumbnail)
                        .apply(block = fun ImageRequest.Builder.() {
                            placeholder(R.drawable.marvel_heroes_item_placeholder)
                            error(R.drawable.broken_image_placeholder)
                        }).build()
                ),
                contentDescription = "Avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(photo) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .constrainAs(spacerTop) {
                    top.linkTo(parent.top)
                })

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .constrainAs(spacerBottom) {
                    bottom.linkTo(parent.bottom)
                })

            Spacer(modifier = Modifier
                .fillMaxHeight()
                .width(6.dp)
                .constrainAs(spacerStart) {
                    start.linkTo(parent.start)
                })

            Spacer(modifier = Modifier
                .fillMaxHeight()
                .width(6.dp)
                .constrainAs(spacerEnd) {
                    end.linkTo(parent.end)
                })

            Text(
                text = character.name,
                color = Color.White,
                fontSize = 10.sp,
                modifier = Modifier
                    .background(Color.DarkGray, RoundedCornerShape(4.dp))
                    .border(1.dp, Color.LightGray, RoundedCornerShape(4.dp))
                    .padding(top = 1.dp, start = 3.dp, bottom = 1.dp, end = 3.dp)
                    .constrainAs(name) {
                        top.linkTo(spacerTop.bottom)
                        start.linkTo(spacerStart.end)
                    }
            )
            Text(
                text = character.comicsListSize.toString().plus(stringResource(R.string.published_comics_append_message)),
                color = Color.White,
                fontSize = 10.sp,
                modifier = Modifier
                    .background(Color.DarkGray, RoundedCornerShape(4.dp))
                    .border(1.dp, Color.LightGray, RoundedCornerShape(4.dp))
                    .padding(top = 1.dp, start = 3.dp, bottom = 1.dp, end = 3.dp)
                    .constrainAs(comics) {
                        bottom.linkTo(spacerBottom.top)
                        start.linkTo(spacerStart.end)
                    }
            )
        }
    }
}