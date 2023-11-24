package com.example.todolist.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist.TaskViewModel
import com.example.todolist.db.Task
import kotlinx.coroutines.launch

@Composable
fun TaskList(viewModel: TaskViewModel) {

    val tasks by viewModel.tasks.collectAsState()

    LazyColumn {
        items(tasks) { task ->
            TaskItem(task, viewModel)
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskItem(task: Task, viewModel: TaskViewModel) {

    val coroutineScope = rememberCoroutineScope()
    val dismissState = rememberDismissState(
        confirmValueChange = {
            if (it == DismissValue.DismissedToEnd || it == DismissValue.DismissedToStart) {
                coroutineScope.launch {
                    viewModel.removeTask(task)
                }
            }
            true
        }
    )

    SwipeToDismissBox(state = dismissState,
        backgroundContent = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color.Red
                )
            }
        },
        directions = setOf(DismissDirection.StartToEnd, DismissDirection.EndToStart),
        content = {
            Card(
                Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(vertical = 5.dp)
            ) {
                Box(Modifier.padding(10.dp)) {
                    Column {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .height(40.dp), verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = task.name,
                                fontSize = 24.sp,
                                modifier = Modifier
                            )
                            Row(
                                Modifier.fillMaxWidth(),
                                Arrangement.End,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = task.date.toString())
                                Checkbox(checked = task.status, onCheckedChange = {
                                    coroutineScope.launch {
                                        viewModel.changeTaskStatus(it, task)
                                    }
                                })
                            }
                        }

                        Text(
                            text = task.description,
                            fontSize = 14.sp,
                            modifier = Modifier.verticalScroll(rememberScrollState())
                        )
                    }
                }
            }
        })
}
