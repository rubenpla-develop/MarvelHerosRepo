package com.rpla.marvelherosrepo.ui.common

import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.rpla.marvelherosrepo.ui.theme.PinkA400
import com.rpla.marvelherosrepo.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAppBar(title: String, openFilters: () -> Unit, modifier: Modifier) {
    val context = LocalContext.current
    TopAppBar(title = { Text(text = title) },
        actions = {
            IconButton(onClick = {
                Toast.makeText(context, "Filter button click", Toast.LENGTH_SHORT).show()
                openFilters()
            }) {
                Icon(imageVector =  Icons.Filled.FilterList, contentDescription =  "Filter", tint = White)
            }
        },
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(containerColor = PinkA400, titleContentColor = White)
    )
}