package com.example.to_do_compose.taskScreen

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.to_do_compose.R
import com.example.to_do_compose.components.DisplayAlertDialog
import com.example.to_do_compose.models.Priority
import com.example.to_do_compose.models.ToDoTask
import com.example.to_do_compose.ui.theme.topAppBarBackgroundColor
import com.example.to_do_compose.ui.theme.topAppBarContentColor
import com.example.to_do_compose.util.Action

@Composable
fun TaskAppBar(
    selectedTask: ToDoTask?,
    navigateToListScreen: (Action) -> Unit

) {
    if(selectedTask == null) {
        NewTopAppBar(navigateToListScreen = navigateToListScreen)
    } else {
        ExistingTopAppBar(
            selectedTask = selectedTask,
            navigateToListScreen = navigateToListScreen
        )
    }
}

@Composable
fun NewTopAppBar(
    navigateToListScreen: (Action) -> Unit
) {
    TopAppBar(
        navigationIcon = {
            BackAction(onBackClicked = navigateToListScreen)
        },
        title = {
            Text(
                text = stringResource(R.string.add_task),
                color = MaterialTheme.colors.topAppBarContentColor
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {
            AddAction(onAddClicked = navigateToListScreen)
        }
    )
}


@Composable
fun BackAction(
    onBackClicked: (Action) -> Unit
) {
    IconButton(
        onClick = { onBackClicked(Action.NO_ACTION) }
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = stringResource(R.string.back_icon),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun AddAction(
    onAddClicked: (Action) -> Unit
) {
    IconButton(
        onClick = { onAddClicked(Action.ADD) }
    ) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(R.string.check_icon),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun ExistingTopAppBar(
    selectedTask: ToDoTask,
    navigateToListScreen: (Action) -> Unit
) {
    TopAppBar(
        navigationIcon = {
            CloseAction(onCloseClicked = navigateToListScreen)
        },
        title = {
            Text(
                text = selectedTask.title,
                color = MaterialTheme.colors.topAppBarContentColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {
          ExistingAppBarActions(
              selectedTask = selectedTask,
              navigateToListScreen = navigateToListScreen)
        }
    )
}

@Composable
fun ExistingAppBarActions(
    selectedTask: ToDoTask,
    navigateToListScreen: (Action) -> Unit

) {
    var openDialog by remember { mutableStateOf(false) }

    DisplayAlertDialog(
        title = stringResource(id = R.string.delete_task, selectedTask.title),
        message = stringResource(
            id = R.string.delete_task_confirmation,
            selectedTask.title),
        openDialog = openDialog,
        closeDialog = {
            openDialog = false
        },
        onYesClicked = { navigateToListScreen(Action.DELETE) }

    )

    DeleteAction(onDeleteClicked = {
        openDialog = true
    })
    UpdateAction(onUpdateClicked = navigateToListScreen)
}

@Composable
fun CloseAction(
    onCloseClicked: (Action) -> Unit
) {
    IconButton(
        onClick = { onCloseClicked(Action.NO_ACTION) }
    ) {
        Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = stringResource(R.string.close_icon),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun DeleteAction(
    onDeleteClicked: () -> Unit
) {
    IconButton(
        onClick = { onDeleteClicked() }
    ) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(R.string.delete_icon),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun UpdateAction(
    onUpdateClicked: (Action) -> Unit
) {
    IconButton(
        onClick = { onUpdateClicked(Action.UPDATE) }
    ) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(R.string.check_icon),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}


@Preview
@Composable
fun TaskAppBarPreview() {
    TaskAppBar(
        selectedTask = ToDoTask(2, "New Title", " New Description", Priority.MEDIUM),
        navigateToListScreen = {})
}

@Preview
@Composable
fun ExistingAppBarPreview() {
    ExistingTopAppBar(
        selectedTask = ToDoTask(2, "Title", "Description", Priority.LOW),
        navigateToListScreen = {})
}

