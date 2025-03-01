package com.example.todo.service

import com.example.todo.model.ToDo
import com.example.todo.repository.ToDoRepository
import org.springframework.stereotype.Service

@Service
class ToDoService(private val toDoRepository: ToDoRepository) {

    fun createTask(toDo: ToDo) {
        toDoRepository.save(toDo)
    }

    fun getAllTasks(): List<ToDo> {
        return toDoRepository.findAll()
    }

    fun deleteTask(id: Int) {
        toDoRepository.deleteById(id)
    }

    fun updateTask(id: Int, toDo: ToDo) {
        if (toDoRepository.existsById(id)) {
            toDoRepository.save(toDo)
        } else {
            throw IllegalArgumentException("Task with ID $id not found")
        }
    }
}
