package com.example.taskmanager.entity

import jakarta.persistence.*

@Entity
data class Task(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val title: String = "",

    val completed: Boolean = false
)