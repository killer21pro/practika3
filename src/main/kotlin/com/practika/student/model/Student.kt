package com.practika.student.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "students")
data class Student(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    
    @Column(nullable = false)
    var name: String = "",
    
    @OneToMany(mappedBy = "student", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var hobbies: MutableList<Hobby> = mutableListOf(),
    
    @Column(nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),
    
    @Column(nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
) {
    @PreUpdate
    fun preUpdate() {
        updatedAt = LocalDateTime.now()
    }
}


