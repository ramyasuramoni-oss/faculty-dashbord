package com.faculty.faculty_kpi_backend.entity;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "student_feedback")
public class StudentFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private Integer feedbackId;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    @Column(name = "academic_year")
    private String academicYear;

    @Column(name = "rating")
    private BigDecimal rating;

    @Column(name = "total_responses")
    private Integer totalResponses;

    public StudentFeedback() {
    }

    public Integer getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public Integer getTotalResponses() {
        return totalResponses;
    }

    public void setTotalResponses(Integer totalResponses) {
        this.totalResponses = totalResponses;
    }
}