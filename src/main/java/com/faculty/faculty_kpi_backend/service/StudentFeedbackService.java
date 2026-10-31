package com.faculty.faculty_kpi_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.faculty.faculty_kpi_backend.entity.StudentFeedback;
import com.faculty.faculty_kpi_backend.repository.StudentFeedbackRepository;

@Service
public class StudentFeedbackService {

    private final StudentFeedbackRepository feedbackRepository;

    public StudentFeedbackService(
            StudentFeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public List<StudentFeedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    public StudentFeedback getFeedbackById(Integer id) {
        return feedbackRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Student feedback not found with ID: " + id
                    )
                );
    }

    public StudentFeedback addFeedback(
            StudentFeedback feedback) {

        return feedbackRepository.save(feedback);
    }

    public StudentFeedback updateFeedback(
            Integer id,
            StudentFeedback feedback) {

        StudentFeedback existing =
                feedbackRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Student feedback not found with ID: " + id
                    )
                );

        existing.setFaculty(feedback.getFaculty());
        existing.setAcademicYear(feedback.getAcademicYear());
        existing.setRating(feedback.getRating());
        existing.setTotalResponses(feedback.getTotalResponses());

        return feedbackRepository.save(existing);
    }

    public void deleteFeedback(Integer id) {

        if (!feedbackRepository.existsById(id)) {
            throw new RuntimeException(
                "Student feedback not found with ID: " + id
            );
        }

        feedbackRepository.deleteById(id);
    }
}