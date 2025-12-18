package com.practika.student.controller

import com.practika.student.dto.CreateStudentRequest
import com.practika.student.dto.StudentDTO
import com.practika.student.service.StudentService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
@RequestMapping("/students")
class StudentController(
    private val studentService: StudentService
) {
    
    @GetMapping
    fun getStudents(
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) hobby: String?,
        model: Model
    ): String {
        val students = studentService.getAllStudents(name, hobby)
        model.addAttribute("students", students)
        model.addAttribute("nameFilter", name ?: "")
        model.addAttribute("hobbyFilter", hobby ?: "")
        return "students"
    }
    
    @PostMapping
    fun createStudent(
        @RequestParam name: String,
        @RequestParam hobbies: String,
        redirectAttributes: RedirectAttributes
    ): String {
        val hobbyList = hobbies.split(",").map { it.trim() }.filter { it.isNotEmpty() }
        val request = CreateStudentRequest(name = name, hobbies = hobbyList)
        studentService.createStudent(request)
        redirectAttributes.addFlashAttribute("message", "Student created successfully!")
        return "redirect:/students"
    }
    
    @GetMapping("/api")
    @ResponseBody
    fun getStudentsApi(
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) hobby: String?
    ): List<StudentDTO> {
        return studentService.getAllStudents(name, hobby)
    }
    
    @PostMapping("/api")
    @ResponseBody
    fun createStudentApi(@RequestBody request: CreateStudentRequest): StudentDTO {
        return studentService.createStudent(request)
    }
}

