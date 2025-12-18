package com.practika.student.dto

data class StudentDTO(
    val id: Long,
    val name: String,
    val hobbies: List<String>,
    val createdAt: String,
    val updatedAt: String
)

data class CreateStudentRequest(
    val name: String,
    val hobbies: List<String>
)


