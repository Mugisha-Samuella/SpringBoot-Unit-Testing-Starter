package com.app.School_MIS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.School_MIS.models.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByEmail(String email);    
}
