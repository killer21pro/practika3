package com.practika.student.repository

import com.practika.student.model.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface StudentRepository : JpaRepository<Student, Long> {
    
    @Query("""
        SELECT DISTINCT s FROM Student s 
        LEFT JOIN FETCH s.hobbies h
        WHERE (:name IS NULL OR LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%')))
        AND (:hobbyName IS NULL OR EXISTS (
            SELECT 1 FROM Hobby h2 WHERE h2.student = s 
            AND LOWER(h2.name) LIKE LOWER(CONCAT('%', :hobbyName, '%'))
        ))
    """)
    fun findByFilters(
        @Param("name") name: String?,
        @Param("hobbyName") hobbyName: String?
    ): List<Student>
}


