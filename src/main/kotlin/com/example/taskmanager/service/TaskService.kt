package com.example.taskmanager.service

import com.example.taskmanager.entity.Task
import com.example.taskmanager.repository.TaskRepository
import org.springframework.stereotype.Service

@Service
class TaskService(
    private val taskRepository: TaskRepository
) {

    fun getAllTasks(): List<Task> {
        return taskRepository.findAll()
    }

    fun getTaskById(id: Long): Task? {
        return taskRepository.findById(id).orElse(null)
    }

    fun createTask(task: Task): Task {
        return taskRepository.save(task)
    }

    fun updateTask(id: Long, task: Task): Task? {

        if (!taskRepository.existsById(id))
            return null

        return taskRepository.save(task.copy(id = id))
    }

    fun deleteTask(id: Long): Boolean {

        if (!taskRepository.existsById(id))
            return false

        taskRepository.deleteById(id)

        return true
    }
}