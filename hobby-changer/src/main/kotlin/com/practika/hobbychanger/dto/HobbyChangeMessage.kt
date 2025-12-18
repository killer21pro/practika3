package com.practika.hobbychanger.dto

data class HobbyChangeMessage(
    val studentId: Long,
    val studentName: String,
    val oldHobbyName: String,
    val newHobbyName: String,
    val timestamp: Long = System.currentTimeMillis()
)


