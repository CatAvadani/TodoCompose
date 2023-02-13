package com.example.to_do_compose.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.example.to_do_compose.models.Priority
import com.example.to_do_compose.ui.theme.LARGE_PADDING
import com.example.to_do_compose.ui.theme.TOP_APP_BAR_HEIGHT
import com.example.to_do_compose.ui.theme.topAppBarBackgroundColor
import com.example.to_do_compose.ui.theme.topAppBarContentColor
import com.example.to_do_compose.util.SearchAppBarState
import com.example.to_do_compose.viewmodels.SharedViewModel
import com.example.to_do_compose.R
import com.example.to_do_compose.util.TrailingIconState

@Composable
fun ListAppBar(
    sharedViewModel: SharedViewModel,
    searchAppBarState: SearchAppBarState,
    searchTextState: String
) {
    when (searchAppBarState) {
        SearchAppBarState.CLOSED -> {
            DefaultListAppBar(
                onSearchClicked = {
                    sharedViewModel.searchAppBarState.value = SearchAppBarState.OPENED
                },
                onSortClicked = {},
                onDeleteClicked = {}

            )
        }
        else -> {
            SearchAppBar(
                text = searchTextState,
                onTextChange = { newText ->
                    sharedViewModel.searchTextState.value = newText
                },
                onCloseClicked = {
                    sharedViewModel.searchAppBarState.value = SearchAppBarState.CLOSED
                    sharedViewModel.searchTextState.value = ""
                },
                onSearchClicked = {}
            )
        }
    }
}

@Composable
fun DefaultListAppBar(
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteClicked: () -> Unit


) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.text_tasks),
                color = MaterialTheme.colors.topAppBarContentColor
            )
        },
        actions = {
            ListAppBarActions(
                onSearchClicked = onSearchClicked,
                onSortClicked = onSortClicked,
                onDeleteClicked = onDeleteClicked
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,

        )
}

@Composable
fun ListAppBarActions(
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteClicked: () -> Unit

) {
    SearchAction(onSearchClicked = onSearchClicked)
    SortAction(onSortClicked = onSortClicked)
    DeleteAllAction(onDeleteClicked = onDeleteClicked)


}

@Composable
fun SearchAction(
    onSearchClicked: () -> Unit
) {
    IconButton(onClick = { onSearchClicked() }) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "",
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun SortAction(
    onSortClicked: (Priority) -> Unit,

    ) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    IconButton(onClick = { isExpanded = true }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_filter_list),
            contentDescription = stringResource(R.string.filter_list),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }) {
            DropdownMenuItem(
                onClick = {
                    isExpanded = false
                    onSortClicked(Priority.LOW)
                }

            ) {
                PriorityItem(priority = Priority.LOW)
            }
            DropdownMenuItem(
                onClick = {
                    isExpanded = false
                    onSortClicked(Priority.HIGH)

                }
            ) {
                PriorityItem(priority = Priority.HIGH)
            }
            DropdownMenuItem(
                onClick = {
                    isExpanded = false
                    onSortClicked(Priority.NONE)

                }
            ) {
                PriorityItem(priority = Priority.NONE)
            }

        }
    }
}

@Composable
fun DeleteAllAction(
    onDeleteClicked: () -> Unit
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    IconButton(onClick = { isExpanded = true }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_vertical_menu),
            contentDescription = stringResource(R.string.delete_all_action),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            DropdownMenuItem(
                onClick = {
                    isExpanded = false
                    onDeleteClicked()
                }) {
                Text(
                    modifier = Modifier
                        .padding(LARGE_PADDING),
                    text = stringResource(R.string.delete_all_action),
                    style = MaterialTheme.typography.subtitle2
                )

            }
        }

    }
}

@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit
) {
    var trailingIconState by remember {
        mutableStateOf(TrailingIconState.READY_TO_DELETE)
    }

    Surface(
        modifier = Modifier
            .height(TOP_APP_BAR_HEIGHT)
            .fillMaxSize(),

        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colors.topAppBarBackgroundColor

    ) {

        TextField(
            modifier = Modifier.fillMaxSize(),
            value = text,
            onValueChange = {
                onTextChange(it)
            },
            placeholder = {
                Text(
                    modifier = Modifier.alpha(ContentAlpha.medium),
                    text = stringResource(R.string.text_search),
                    color = Color.White
                )
            },
            textStyle = TextStyle(
                color = MaterialTheme.colors.topAppBarContentColor,
                fontSize = MaterialTheme.typography.subtitle1.fontSize
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(
                    onClick = { },
                    modifier = Modifier.alpha(ContentAlpha.disabled)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = stringResource(id = R.string.search_icon),
                        tint = MaterialTheme.colors.topAppBarContentColor
                    )
                }

            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        when(trailingIconState) {
                            TrailingIconState.READY_TO_DELETE -> {
                                  onTextChange("")
                                trailingIconState = TrailingIconState.READY_TO_CLOSE
                            }
                            TrailingIconState.READY_TO_CLOSE -> {
                              if (text.isNotEmpty()) {
                                  onTextChange("")
                              } else {
                                  onCloseClicked()
                                  trailingIconState = TrailingIconState.READY_TO_DELETE
                              }
                            }
                        }
                    }
                ) {

                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = stringResource(R.string.icon_close),
                        tint = MaterialTheme.colors.topAppBarContentColor
                    )

                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(text)
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = MaterialTheme.colors.topAppBarContentColor,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                backgroundColor = Color.Transparent
            )
        )

    }
}

@Preview
@Composable
fun SearchAppBarPreview() {
    SearchAppBar(text = "Search", onTextChange = {}, onCloseClicked = { }, onSearchClicked = {})


}


@Preview
@Composable
fun DefaultListAppBarPreview() {
    DefaultListAppBar(
        onSearchClicked = {},
        onSortClicked = {},
        onDeleteClicked = {}
    )
}