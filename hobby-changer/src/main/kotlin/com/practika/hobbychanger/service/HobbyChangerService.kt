package com.practika.hobbychanger.service

import com.practika.hobbychanger.dto.HobbyChangeMessage
import com.practika.hobbychanger.kafka.HobbyChangeProducer
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class HobbyChangerService(
    private val hobbyChangeProducer: HobbyChangeProducer
) {
    private val logger = LoggerFactory.getLogger(javaClass)
    
    private val randomHobbies = listOf(
        "Reading", "Swimming", "Coding", "Painting", "Music",
        "Dancing", "Cooking", "Traveling", "Photography", "Gaming",
        "Cycling", "Yoga", "Writing", "Gardening", "Chess",
        "Singing", "Drawing", "Running", "Skiing", "Fishing"
    )
    
    fun generateRandomHobby(exclude: String?): String {
        val availableHobbies = randomHobbies.filter { it != exclude }
        return availableHobbies.random()
    }
    
    fun changeHobby(message: HobbyChangeMessage) {
        // Generate a new random hobby excluding the old one
        val newHobby = generateRandomHobby(message.oldHobbyName)
        val updatedMessage = message.copy(newHobbyName = newHobby)
        
        logger.info("Changing hobby for student {} from {} to {}", 
            message.studentName, message.oldHobbyName, newHobby)
        
        hobbyChangeProducer.sendHobbyChangeMessage(updatedMessage)
    }
    
}

