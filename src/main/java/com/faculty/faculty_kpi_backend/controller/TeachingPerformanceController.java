package com.faculty.faculty_kpi_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.faculty.faculty_kpi_backend.entity.TeachingPerformance;
import com.faculty.faculty_kpi_backend.service.TeachingPerformanceService;

@RestController
@RequestMapping("/api/teaching-performance")
public class TeachingPerformanceController {

    private final TeachingPerformanceService teachingService;

    public TeachingPerformanceController(
            TeachingPerformanceService teachingService) {
        this.teachingService = teachingService;
    }

    @GetMapping
    public ResponseEntity<List<TeachingPerformance>>
            getAllTeachingPerformance() {

        return ResponseEntity.ok(
                teachingService.getAllTeachingPerformance()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeachingPerformance>
            getTeachingPerformanceById(
                    @PathVariable Integer id) {

        return ResponseEntity.ok(
                teachingService.getTeachingPerformanceById(id)
        );
    }

    @PostMapping
    public ResponseEntity<TeachingPerformance>
            addTeachingPerformance(
                    @RequestBody TeachingPerformance teachingPerformance) {

        TeachingPerformance saved =
                teachingService.addTeachingPerformance(
                        teachingPerformance
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeachingPerformance>
            updateTeachingPerformance(
                    @PathVariable Integer id,
                    @RequestBody TeachingPerformance teachingPerformance) {

        return ResponseEntity.ok(
                teachingService.updateTeachingPerformance(
                        id, teachingPerformance
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeachingPerformance(
            @PathVariable Integer id) {

        teachingService.deleteTeachingPerformance(id);

        return ResponseEntity.noContent().build();
    }
}