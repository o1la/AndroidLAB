package com.example.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.todolist.ui.TaskList
import com.example.todolist.ui.theme.TodoListTheme
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val taskViewModel: TaskViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           KoinAndroidContext {
               TodoListTheme {
                   Surface(
                       modifier = Modifier.fillMaxSize(),
                       color = MaterialTheme.colorScheme.background
                   ) {
                       TaskList(taskViewModel)
                   }
               }
           }
        }
    }
}
