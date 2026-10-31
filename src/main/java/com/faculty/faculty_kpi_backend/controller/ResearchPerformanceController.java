package com.faculty.faculty_kpi_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.faculty.faculty_kpi_backend.entity.ResearchPerformance;
import com.faculty.faculty_kpi_backend.service.ResearchPerformanceService;

@RestController
@RequestMapping("/api/research-performance")
public class ResearchPerformanceController {

    private final ResearchPerformanceService researchService;

    public ResearchPerformanceController(
            ResearchPerformanceService researchService) {
        this.researchService = researchService;
    }

    @GetMapping
    public ResponseEntity<List<ResearchPerformance>>
            getAllResearchPerformance() {

        return ResponseEntity.ok(
                researchService.getAllResearchPerformance()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResearchPerformance>
            getResearchPerformanceById(
                    @PathVariable Integer id) {

        return ResponseEntity.ok(
                researchService.getResearchPerformanceById(id)
        );
    }

    @PostMapping
    public ResponseEntity<ResearchPerformance>
            addResearchPerformance(
                    @RequestBody ResearchPerformance researchPerformance) {

        ResearchPerformance saved =
                researchService.addResearchPerformance(
                        researchPerformance
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResearchPerformance>
            updateResearchPerformance(
                    @PathVariable Integer id,
                    @RequestBody ResearchPerformance researchPerformance) {

        return ResponseEntity.ok(
                researchService.updateResearchPerformance(
                        id, researchPerformance
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResearchPerformance(
            @PathVariable Integer id) {

        researchService.deleteResearchPerformance(id);

        return ResponseEntity.noContent().build();
    }
}