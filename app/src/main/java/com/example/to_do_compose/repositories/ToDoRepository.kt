package com.example.to_do_compose.repositories

import com.example.to_do_compose.data.ToDoDao
import com.example.to_do_compose.models.ToDoTask
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class ToDoRepository @Inject constructor(private val toDoDao: ToDoDao) {

    val getAllTasks : Flow<List<ToDoTask>> = toDoDao.getAllTheTasks()
    val sortByLowPriority : Flow<List<ToDoTask>> = toDoDao.sortByLowPriority()
    val sortByHighPriority : Flow<List<ToDoTask>> = toDoDao.sortByHighPriority()

    fun getSelectedTask(taskId: Int): Flow<ToDoTask> = toDoDao.getSelectedTask(taskId)

    suspend fun addTask(toDoTask: ToDoTask) = toDoDao.addTask(toDoTask)
    suspend fun upDateTask(toDoTask: ToDoTask) = toDoDao.upDateTask(toDoTask)
    suspend fun deleteTask(toDoTask: ToDoTask) = toDoDao.deleteTask(toDoTask)
    suspend fun deleteAllTask() = toDoDao.deleteAllTasks()

    fun searchDatabase(searchQuery: String) = toDoDao.searchDatabase(searchQuery)

}