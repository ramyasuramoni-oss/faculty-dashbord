package com.faculty.faculty_kpi_backend.controller;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faculty.faculty_kpi_backend.service.KpiCalculationService;
import com.faculty.faculty_kpi_backend.dto.FacultyKpiResponse;

@RestController
@RequestMapping("/api/kpi-test")
public class KpiTestController {

    private final KpiCalculationService kpiCalculationService;

    public KpiTestController(KpiCalculationService kpiCalculationService) {
        this.kpiCalculationService = kpiCalculationService;
    }

    @GetMapping("/teaching/{facultyId}")
    public ResponseEntity<BigDecimal> getTeachingScore(
            @PathVariable Integer facultyId) {

        return ResponseEntity.ok(
                kpiCalculationService.calculateTeachingScore(facultyId)
        );
    }

    @GetMapping("/research/{facultyId}")
    public ResponseEntity<BigDecimal> getResearchScore(
            @PathVariable Integer facultyId) {

        return ResponseEntity.ok(
                kpiCalculationService.calculateResearchScore(facultyId)
        );
    }

    @GetMapping("/feedback/{facultyId}")
    public ResponseEntity<BigDecimal> getFeedbackScore(
            @PathVariable Integer facultyId) {

        return ResponseEntity.ok(
                kpiCalculationService.calculateFeedbackScore(facultyId)
        );
    }

    @GetMapping("/professional/{facultyId}")
    public ResponseEntity<BigDecimal> getProfessionalScore(
            @PathVariable Integer facultyId) {

        return ResponseEntity.ok(
                kpiCalculationService.calculateProfessionalScore(facultyId)
        );
    }

    @GetMapping("/contribution/{facultyId}")
    public ResponseEntity<BigDecimal> getContributionScore(
            @PathVariable Integer facultyId) {

        return ResponseEntity.ok(
                kpiCalculationService.calculateContributionScore(facultyId)
        );
    }
    
    @GetMapping("/overall/{facultyId}")
    public ResponseEntity<BigDecimal> getOverallKpi(
            @PathVariable Integer facultyId) {

        return ResponseEntity.ok(
                kpiCalculationService.calculateOverallKpi(facultyId)
        );
    }
    
    @GetMapping("/summary/{facultyId}")
    public ResponseEntity<FacultyKpiResponse> getFacultyKpiSummary(
            @PathVariable Integer facultyId) {

        return ResponseEntity.ok(
                kpiCalculationService.getFacultyKpiSummary(facultyId)
        );
    }
    
    @GetMapping("/summary")
    public ResponseEntity<List<FacultyKpiResponse>> getAllFacultyKpiSummary() {

        return ResponseEntity.ok(
                kpiCalculationService.getAllFacultyKpiSummary()
        );
    }
}
