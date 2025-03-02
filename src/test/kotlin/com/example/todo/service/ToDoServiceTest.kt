package com.example.todo.service

import com.example.todo.model.ToDo
import com.example.todo.repository.ToDoRepository
import io.mockk.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

class ToDoServiceTest {

    private lateinit var toDoService: ToDoService
    private val toDoRepository: ToDoRepository = mockk()

    @BeforeEach
    fun setUp() {
        toDoService = ToDoService(toDoRepository)
    }

    @Test
    fun `createTask should save ToDo in repository`() {
        val task = ToDo(id = 1, task = "Test Task", isCompleted = false)
        every { toDoRepository.save(task) } returns task

        toDoService.createTask(task)

        verify { toDoRepository.save(task) }
    }

    @Test
    fun `getAllTasks should return list of ToDo`() {
        val tasks = listOf(ToDo(id = 1, task = "Task 1", isCompleted = false))
        every { toDoRepository.findAll() } returns tasks

        val result = toDoService.getAllTasks()

        assertEquals(tasks, result)
        verify { toDoRepository.findAll() }
    }

    @Test
    fun `deleteTask should remove task if it exists`() {
        every { toDoRepository.deleteById(1) } just Runs

        toDoService.deleteTask(1)

        verify { toDoRepository.deleteById(1) }
    }

    @Test
    fun `updateTask should update an existing task`() {
        val task = ToDo(id = 1, task = "Updated Task", isCompleted = true)
        every { toDoRepository.existsById(1) } returns true
        every { toDoRepository.save(task) } returns task

        toDoService.updateTask(1, task)

        verify { toDoRepository.save(task) }
    }

    @Test
    fun `updateTask should throw exception when task does not exist`() {
        val task = ToDo(id = 2, task = "Non-Existent Task", isCompleted = false)
        every { toDoRepository.existsById(2) } returns false

        val exception = assertThrows<IllegalArgumentException> {
            toDoService.updateTask(2, task)
        }

        assertEquals("Task with ID 2 not found", exception.message)
    }
}
