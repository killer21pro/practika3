package com.practika.student.controller

import com.practika.student.dto.HobbyChangeMessage
import com.practika.student.kafka.HobbyChangeProducer
import com.practika.student.service.StudentService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@RestController
@RequestMapping("/api/hobby-change")
class HobbyChangeController(
    private val studentService: StudentService,
    private val hobbyChangeProducer: HobbyChangeProducer
) {
    
    @PostMapping("/{studentId}")
    fun requestHobbyChange(
        @PathVariable studentId: Long,
        @RequestParam hobbyName: String
    ): ResponseEntity<Map<String, String>> {
        val student = studentService.getStudentById(studentId)
        if (student == null) {
            return ResponseEntity.notFound().build()
        }
        
        val message = HobbyChangeMessage(
            studentId = studentId,
            studentName = student.name,
            oldHobbyName = hobbyName,
            newHobbyName = hobbyName // Will be changed by hobby-changer service
        )
        
        hobbyChangeProducer.sendHobbyChangeMessage(message)
        
        return ResponseEntity.ok(mapOf(
            "message" to "Hobby change request sent for student ${student.name}",
            "studentId" to studentId.toString(),
            "hobbyName" to hobbyName
        ))
    }
}

// Controller for web form submission (redirects back to students page)
@Controller
@RequestMapping("/hobby-change")
class HobbyChangeWebController(
    private val studentService: StudentService,
    private val hobbyChangeProducer: HobbyChangeProducer
) {
    @PostMapping("/{studentId}")
    fun requestHobbyChangeWeb(
        @PathVariable studentId: Long,
        @RequestParam hobbyName: String,
        redirectAttributes: RedirectAttributes
    ): String {
        val student = studentService.getStudentById(studentId)
        if (student != null) {
            val message = HobbyChangeMessage(
                studentId = studentId,
                studentName = student.name,
                oldHobbyName = hobbyName,
                newHobbyName = hobbyName
            )
            hobbyChangeProducer.sendHobbyChangeMessage(message)
            redirectAttributes.addFlashAttribute("message", "Hobby change request sent for ${student.name}! Check back in a moment.")
        }
        return "redirect:/students"
    }
}

