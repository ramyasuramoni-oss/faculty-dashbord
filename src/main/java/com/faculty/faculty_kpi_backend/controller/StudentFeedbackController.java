package com.faculty.faculty_kpi_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.faculty.faculty_kpi_backend.entity.StudentFeedback;
import com.faculty.faculty_kpi_backend.service.StudentFeedbackService;

@RestController
@RequestMapping("/api/student-feedback")
public class StudentFeedbackController {

    private final StudentFeedbackService feedbackService;

    public StudentFeedbackController(
            StudentFeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping
    public ResponseEntity<List<StudentFeedback>> getAllFeedback() {

        return ResponseEntity.ok(
                feedbackService.getAllFeedback()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentFeedback> getFeedbackById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                feedbackService.getFeedbackById(id)
        );
    }

    @PostMapping
    public ResponseEntity<StudentFeedback> addFeedback(
            @RequestBody StudentFeedback feedback) {

        StudentFeedback saved =
                feedbackService.addFeedback(feedback);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentFeedback> updateFeedback(
            @PathVariable Integer id,
            @RequestBody StudentFeedback feedback) {

        return ResponseEntity.ok(
                feedbackService.updateFeedback(id, feedback)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(
            @PathVariable Integer id) {

        feedbackService.deleteFeedback(id);

        return ResponseEntity.noContent().build();
    }
}