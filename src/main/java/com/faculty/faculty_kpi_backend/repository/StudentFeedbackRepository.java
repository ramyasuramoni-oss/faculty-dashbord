package com.faculty.faculty_kpi_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.faculty.faculty_kpi_backend.entity.StudentFeedback;

public interface StudentFeedbackRepository
        extends JpaRepository<StudentFeedback, Integer> {

}