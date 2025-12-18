package com.practika.hobbychanger.kafka

import com.practika.hobbychanger.dto.HobbyChangeMessage
import com.practika.hobbychanger.service.HobbyChangerService
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component

@Component
class HobbyChangeListener(
    private val hobbyChangerService: HobbyChangerService
) {
    private val logger = LoggerFactory.getLogger(javaClass)
    
    @KafkaListener(topics = ["hobby-change"], groupId = "hobby-changer-group")
    fun listen(message: HobbyChangeMessage, acknowledgment: Acknowledgment) {
        logger.info("Received hobby change message: {}", message)
        
        try {
            // Change to another random hobby
            hobbyChangerService.changeHobby(message)
            acknowledgment.acknowledge()
        } catch (e: Exception) {
            logger.error("Error processing hobby change message", e)
        }
    }
}


