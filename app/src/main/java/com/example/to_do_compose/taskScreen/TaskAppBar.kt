package com.example.to_do_compose.taskScreen

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.to_do_compose.ui.theme.topAppBarBackgroundColor
import com.example.to_do_compose.ui.theme.topAppBarContentColor
import com.example.to_do_compose.util.Action

@Composable
fun TaskAppBar(
    navigateToListScreen: (Action) -> Unit

) {
 NewTopAppBar(navigateToListScreen =  navigateToListScreen)
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
                text = "Add Task",
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
           contentDescription = "Back Icon",
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
            contentDescription = "Check Icon",
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Preview
@Composable
fun TaskAppBarPreview() {
    TaskAppBar( navigateToListScreen = {})
}
