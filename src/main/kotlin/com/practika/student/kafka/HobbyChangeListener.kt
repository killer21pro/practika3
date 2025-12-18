package com.practika.student.kafka

import com.practika.student.dto.HobbyChangeMessage
import com.practika.student.service.StudentService
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component

@Component
class HobbyChangeListener(
    private val studentService: StudentService
) {
    private val logger = LoggerFactory.getLogger(javaClass)
    
    @KafkaListener(topics = ["hobby-change"], groupId = "student-service-group")
    fun listen(message: HobbyChangeMessage, acknowledgment: Acknowledgment) {
        logger.info("Student service received hobby change message: {}", message)
        
        // Only process if this is a response from hobby-changer (oldHobby != newHobby)
        // If oldHobby == newHobby, it's our initial request, ignore it
        if (message.oldHobbyName != message.newHobbyName) {
            try {
                studentService.updateStudentHobby(
                    studentId = message.studentId,
                    oldHobbyName = message.oldHobbyName,
                    newHobbyName = message.newHobbyName
                )
                logger.info("Successfully updated hobby for student {} from {} to {}", 
                    message.studentName, message.oldHobbyName, message.newHobbyName)
            } catch (e: Exception) {
                logger.error("Error processing hobby change message", e)
            }
        } else {
            logger.debug("Ignoring initial hobby change request (not a response)")
        }
        
        acknowledgment.acknowledge()
    }
}

