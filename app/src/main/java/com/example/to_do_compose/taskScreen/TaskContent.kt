package com.example.to_do_compose.taskScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.to_do_compose.R
import com.example.to_do_compose.components.PriorityDropDown
import com.example.to_do_compose.models.Priority
import com.example.to_do_compose.ui.theme.LARGE_PADDING
import com.example.to_do_compose.ui.theme.MEDIUM_PADDING

@Composable
fun TaskContent(
    title: String,
    onTitleChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    priority: Priority,
    onPrioritySelected: (Priority) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(LARGE_PADDING)
    ) {
     OutlinedTextField(
         modifier = Modifier.fillMaxWidth(),
         value = title,
         onValueChange ={ newTitle ->
             onTitleChange(newTitle)},
         label = {
             Text(
                 text = stringResource(R.string.title),
                 style = MaterialTheme.typography.body1
             )
         },
         singleLine = true
     )
        Spacer(modifier = Modifier.height(MEDIUM_PADDING))
        PriorityDropDown(priority = priority , onPrioritySelected = onPrioritySelected )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxSize()
            ,
            value = description ,
            onValueChange = { newDescription ->
                onDescriptionChange(newDescription)
            },
            label = {
                Text(
                    text = stringResource(id = R.string.description),
                    style = MaterialTheme.typography.body1
                )
            }
        )
    }

}

@Preview(showBackground = true)
@Composable
fun TaskContentPreview() {
    TaskContent(
        title = "",
        onTitleChange ={} ,
        description = "",
        onDescriptionChange = {},
        priority = Priority.LOW,
        onPrioritySelected = {}
    )
}