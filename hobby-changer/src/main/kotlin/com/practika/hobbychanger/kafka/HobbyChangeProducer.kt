package com.practika.hobbychanger.kafka

import com.practika.hobbychanger.dto.HobbyChangeMessage
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class HobbyChangeProducer(
    private val kafkaTemplate: KafkaTemplate<String, Any>
) {
    private val logger = LoggerFactory.getLogger(javaClass)
    
    fun sendHobbyChangeMessage(message: HobbyChangeMessage) {
        try {
            kafkaTemplate.send("hobby-change", message)
            logger.info("Sent hobby change message: {}", message)
        } catch (e: Exception) {
            logger.error("Error sending hobby change message", e)
        }
    }
}


