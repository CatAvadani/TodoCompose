package com.example.to_do_compose.listScreen

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarResult
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
        sharedViewModel.readSortState()
    }

    val action by sharedViewModel.action
    val allTasks by sharedViewModel.allTasks.collectAsState()
    val searchedTasks by sharedViewModel.searchedTasks.collectAsState()
    val sortState by sharedViewModel.sortState.collectAsState()
    val lowPriorityTasks by sharedViewModel.lowPriorityTasks.collectAsState()
    val highPriorityTasks by sharedViewModel.highPriorityTasks.collectAsState()

    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
    val searchTextState: String by sharedViewModel.searchTextState

    val scaffoldState = rememberScaffoldState()
    DisplaySnackBar(
        scaffoldState = scaffoldState ,
        handleDatabaseAction = {
          sharedViewModel.handleDatabaseAction(action = action)
        },
        taskTitle = sharedViewModel.title.value,
        action = action,
        onUndoClicked = {
            sharedViewModel.action.value = it
        }
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
            ListContent(
                allTasks = allTasks,
                searchedTasks = searchedTasks,
                lowPriorityTasks = lowPriorityTasks,
                highPriorityTasks = highPriorityTasks,
                sortState = sortState,
                onSwipeToDelete = { action, toDoTask ->  
                    sharedViewModel.action.value = action
                    sharedViewModel.updateTaskFields(toDoTask)
                },
                searchAppBarState = searchAppBarState,
                navigateToTaskScreen = navigateToTaskScreen
            )
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
    onUndoClicked: (Action) -> Unit,
    taskTitle: String,
    action: Action
) {
    handleDatabaseAction()

    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = action) {
        if (action != Action.NO_ACTION) {
            scope.launch {
               val snackBarResult = scaffoldState.snackbarHostState.showSnackbar(
                   message = setMessage(action = action, taskTitle = taskTitle),
                   actionLabel = setActionLabel(action),
                   )
                undoDeletedTask(
                    action = action,
                    snackBarResult = snackBarResult,
                    onUndoClicked = onUndoClicked
                 )
            }
        }
    }
}

private fun setMessage(action: Action, taskTitle: String): String {
     return  when(action) {
         Action.DELETE_ALL -> "All Tasks Removed"
         else -> "${action.name}: $taskTitle"
     }

}

private fun setActionLabel(action: Action): String{
    return if (action.name == "DELETE") {
        "UNDO"
    } else {
        "OK"
    }

}

private fun undoDeletedTask(
    action: Action,
    snackBarResult: SnackbarResult,
    onUndoClicked: (Action) -> Unit,
) {
   if (
     snackBarResult == SnackbarResult.ActionPerformed &&
     action == Action.DELETE
   ) {
    onUndoClicked(Action.UNDO)
   }
}

















