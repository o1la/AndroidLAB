package com.example.todolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.db.Task
import com.example.todolist.db.TaskDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TaskViewModel(private val taskDao: TaskDao) : ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()

    init {
        fetchList()
    }

    private fun fetchList() {
        viewModelScope.launch {
            taskDao.selectAll().collect { listOfTasks ->
                _tasks.value = listOfTasks
            }
        }
    }

    fun removeTask(task: Task) {
        viewModelScope.launch {
            taskDao.delete(task.id)
        }
    }

    suspend fun changeTaskStatus(newStatus: Boolean, task: Task) {
        taskDao.updateTask(newStatus, task.id)
        _tasks.value = _tasks.value.map {
            if (it.id == task.id) it.copy(status = newStatus) else it
        }
    }

}