package com.example.to_do_compose.listScreen

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import com.example.to_do_compose.components.ListAppBar
import com.example.to_do_compose.components.ListFab
import com.example.to_do_compose.util.Action
import com.example.to_do_compose.util.SearchAppBarState
import com.example.to_do_compose.viewmodels.SharedViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ListScreen(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    LaunchedEffect(key1 = true) {
        sharedViewModel.getAllTasks()
    }

    val action by sharedViewModel.action
    val allTasks by sharedViewModel.allTasks.collectAsState()

    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
    val searchTextState: String by sharedViewModel.searchTextState

    val scaffoldState = rememberScaffoldState()
    DisplaySnackBar(
        scaffoldState = scaffoldState ,
        handleDatabaseAction = {
          sharedViewModel.handleDatabaseAction(action = action)
        },
        taskTitle = sharedViewModel.title.value,
        action = action
    )
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            ListAppBar(
                sharedViewModel = sharedViewModel,
                searchAppBarState = searchAppBarState,
                searchTextState = searchTextState
            )
        },
        content = {
            ListContent(tasks = allTasks, navigateToTaskScreen = navigateToTaskScreen)
        },
        floatingActionButton = {
            ListFab(onFabClicked = navigateToTaskScreen)
        }
    )
}

@Composable
fun DisplaySnackBar(
    scaffoldState: ScaffoldState,
    handleDatabaseAction: () -> Unit,
    taskTitle: String,
    action: Action
) {
    handleDatabaseAction()

    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = action) {
        if (action != Action.NO_ACTION) {
            scope.launch {
               val snackBarResult = scaffoldState.snackbarHostState.showSnackbar(
                   message = "${action.name}: $taskTitle",
                   actionLabel = "Ok",
                   )
            }
        }
    }

}

















