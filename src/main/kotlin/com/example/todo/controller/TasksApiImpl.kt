package com.example.todo.controller

import com.example.todo.api.TasksApi
import com.example.todo.model.Task
import com.example.todo.model.ToDo
import com.example.todo.service.ToDoService
import org.springframework.context.annotation.Primary
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.stereotype.Controller
import org.springframework.stereotype.Service
//import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RestController

//import org.springframework.stereotype.Service

//import org.springframework.web.bind.annotation.RestController

@RestController
class TasksApiImpl(private val toDoService: ToDoService) : TasksApi {

    override fun addTask(task: Task): ResponseEntity<Unit> {
        toDoService.createTask(task.toToDo())
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    override fun getTasks(): ResponseEntity<List<Task>> {
        val tasks = toDoService.getAllTasks().map { it.toTask() }
        return ResponseEntity.ok(tasks)
    }

    override fun deleteTask(id: Int): ResponseEntity<Unit> {
        toDoService.deleteTask(id)
        return ResponseEntity.noContent().build()
    }

    override fun updateTask(id: Int, task: Task): ResponseEntity<Unit> {
        toDoService.updateTask(id, task.toToDo())
        return ResponseEntity.ok().build()
    }

    fun Task.toToDo(): ToDo = ToDo(id = this.id, task = this.task, isCompleted = this.isCompleted)

    fun ToDo.toTask(): Task = Task(id = this.id ?: 0, task = this.task, isCompleted = this.isCompleted)
}