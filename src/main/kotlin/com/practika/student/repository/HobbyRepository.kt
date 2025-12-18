package com.practika.student.repository

import com.practika.student.model.Hobby
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface HobbyRepository : JpaRepository<Hobby, Long> {
    fun findByStudentId(studentId: Long): List<Hobby>
}


