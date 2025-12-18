package com.practika.student.service

import com.practika.student.dto.CreateStudentRequest
import com.practika.student.dto.StudentDTO
import com.practika.student.model.Hobby
import com.practika.student.model.Student
import com.practika.student.repository.StudentRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class StudentService(
    private val studentRepository: StudentRepository
) {
    
    fun getAllStudents(nameFilter: String?, hobbyFilter: String?): List<StudentDTO> {
        val students = if (nameFilter.isNullOrBlank() && hobbyFilter.isNullOrBlank()) {
            studentRepository.findAll()
        } else {
            studentRepository.findByFilters(
                name = if (nameFilter.isNullOrBlank()) null else nameFilter,
                hobbyName = if (hobbyFilter.isNullOrBlank()) null else hobbyFilter
            )
        }
        
        return students.map { student ->
            StudentDTO(
                id = student.id,
                name = student.name,
                hobbies = student.hobbies.map { it.name },
                createdAt = student.createdAt.toString(),
                updatedAt = student.updatedAt.toString()
            )
        }
    }
    
    fun createStudent(request: CreateStudentRequest): StudentDTO {
        val student = Student(name = request.name)
        val hobbies = request.hobbies.map { hobbyName ->
            Hobby(name = hobbyName, student = student)
        }
        student.hobbies = hobbies.toMutableList()
        
        val saved = studentRepository.save(student)
        
        return StudentDTO(
            id = saved.id,
            name = saved.name,
            hobbies = saved.hobbies.map { it.name },
            createdAt = saved.createdAt.toString(),
            updatedAt = saved.updatedAt.toString()
        )
    }
    
    fun getStudentById(id: Long): Student? {
        return studentRepository.findById(id).orElse(null)
    }
    
    fun updateStudentHobby(studentId: Long, oldHobbyName: String, newHobbyName: String): StudentDTO? {
        val student = studentRepository.findById(studentId).orElse(null) ?: return null
        
        val hobby = student.hobbies.find { it.name == oldHobbyName }
        if (hobby != null) {
            hobby.name = newHobbyName
            studentRepository.save(student)
        }
        
        return StudentDTO(
            id = student.id,
            name = student.name,
            hobbies = student.hobbies.map { it.name },
            createdAt = student.createdAt.toString(),
            updatedAt = student.updatedAt.toString()
        )
    }
    
}

