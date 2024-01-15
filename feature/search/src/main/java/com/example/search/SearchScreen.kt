package com.example.search

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.designsystem.component.WeatherBackground
import com.example.designsystem.component.WeatherTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    searchVM: SearchScreenViewModel = hiltViewModel(),
    snackbarState: SnackbarHostState,
    snackbarText: String
) {

    WeatherBackground(
        modifier = Modifier
            .wrapContentHeight()
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                WeatherTopAppBar(
                    modifier = Modifier.fillMaxWidth(),
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background),
                    title = {
                        Text(
                            text = "Search",
                            maxLines = 1,
                            overflow = TextOverflow.Clip
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                searchVM.reducer(intent = SearchScreenIntent.NavigateBack)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = null
                            )
                        }
                    }
                )
            },
            snackbarHost = {
                SnackbarHost(
                    hostState = snackbarState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                ) {
                    Snackbar {
                        Text(text = snackbarText)
                    }
                }
            },
            floatingActionButton = { },
            floatingActionButtonPosition = FabPosition.End
        ) { padding ->
            SearchScreenMain(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            )
        }
    }

}

@Composable
private fun SearchScreenMain(modifier: Modifier) {
    Text(modifier = modifier, text = "Search screen")
}