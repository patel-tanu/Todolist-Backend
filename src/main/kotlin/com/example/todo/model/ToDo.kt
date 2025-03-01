
package com.example.todo.model

import jakarta.persistence.*

@Entity
@Table(name = "tasks")
data class ToDo(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,


    @Column(nullable = false) // Ensures non-null in DB
    val task: String,


    @Column(nullable = false) // Ensures non-null in DB
    val isCompleted: Boolean = false
)
