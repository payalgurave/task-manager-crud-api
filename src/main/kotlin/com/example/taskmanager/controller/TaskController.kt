package com.example.taskmanager.controller

import com.example.taskmanager.entity.Task
import com.example.taskmanager.service.TaskService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/tasks")
class TaskController(
    private val taskService: TaskService
) {

    // GET all tasks
    @GetMapping
    fun getAllTasks(): List<Task> {
        return taskService.getAllTasks()
    }

    // GET task by ID
    @GetMapping("/{id}")
    fun getTaskById(@PathVariable id: Long): ResponseEntity<Task> {

        val task = taskService.getTaskById(id)

        return if (task != null) {
            ResponseEntity.ok(task)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    // CREATE task
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createTask(@RequestBody task: Task): Task {
        return taskService.createTask(task)
    }

    // UPDATE task
    @PutMapping("/{id}")
    fun updateTask(
        @PathVariable id: Long,
        @RequestBody task: Task
    ): ResponseEntity<Task> {

        val updatedTask = taskService.updateTask(id, task)

        return if (updatedTask != null) {
            ResponseEntity.ok(updatedTask)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    // DELETE task
    @DeleteMapping("/{id}")
    fun deleteTask(@PathVariable id: Long): ResponseEntity<Void> {

        return if (taskService.deleteTask(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}