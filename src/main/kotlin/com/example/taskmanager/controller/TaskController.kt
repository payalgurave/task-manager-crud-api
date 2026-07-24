package com.example.taskmanager.controller

import com.example.taskmanager.entity.Task
import com.example.taskmanager.repository.TaskRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/tasks")
class TaskController(
    private val taskRepository: TaskRepository
) {

    @GetMapping
    fun getAllTasks(): List<Task> {
        return taskRepository.findAll()
    }

    @GetMapping("/{id}")
    fun getTaskById(@PathVariable id: Long): ResponseEntity<Task> {

        val task = taskRepository.findById(id)

        return if (task.isPresent) {
            ResponseEntity.ok(task.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createTask(@RequestBody task: Task): Task {
        return taskRepository.save(task)
    }

    @PutMapping("/{id}")
    fun updateTask(
        @PathVariable id: Long,
        @RequestBody updatedTask: Task
    ): ResponseEntity<Task> {

        if (!taskRepository.existsById(id)) {
            return ResponseEntity.notFound().build()
        }

        val task = updatedTask.copy(id = id)

        return ResponseEntity.ok(taskRepository.save(task))
    }

    @DeleteMapping("/{id}")
    fun deleteTask(@PathVariable id: Long): ResponseEntity<Void> {

        if (!taskRepository.existsById(id)) {
            return ResponseEntity.notFound().build()
        }

        taskRepository.deleteById(id)

        return ResponseEntity.noContent().build()
    }
}