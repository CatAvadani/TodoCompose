package com.example.to_do_compose.listScreen

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.to_do_compose.components.ListAppBar
import com.example.to_do_compose.components.ListFab
import com.example.to_do_compose.util.SearchAppBarState
import com.example.to_do_compose.viewmodels.SharedViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ListScreen(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
    val searchTextState: String by sharedViewModel.searchTextState
    Scaffold(
        topBar = {
         ListAppBar(
             sharedViewModel = sharedViewModel,
             searchAppBarState = searchAppBarState,
             searchTextState = searchTextState
         )
        },
        content = {},
        floatingActionButton = {
            ListFab(onFabClicked = navigateToTaskScreen)
        }
    )

}

