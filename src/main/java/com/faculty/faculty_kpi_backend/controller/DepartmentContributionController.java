package com.faculty.faculty_kpi_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.faculty.faculty_kpi_backend.entity.DepartmentContribution;
import com.faculty.faculty_kpi_backend.service.DepartmentContributionService;

@RestController
@RequestMapping("/api/department-contribution")
public class DepartmentContributionController {

    private final DepartmentContributionService contributionService;

    public DepartmentContributionController(
            DepartmentContributionService contributionService) {
        this.contributionService = contributionService;
    }

    @GetMapping
    public ResponseEntity<List<DepartmentContribution>>
            getAllContributions() {

        return ResponseEntity.ok(
                contributionService.getAllContributions()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentContribution>
            getContributionById(
                    @PathVariable Integer id) {

        return ResponseEntity.ok(
                contributionService.getContributionById(id)
        );
    }

    @PostMapping
    public ResponseEntity<DepartmentContribution>
            addContribution(
                    @RequestBody DepartmentContribution contribution) {

        DepartmentContribution saved =
                contributionService.addContribution(contribution);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentContribution>
            updateContribution(
                    @PathVariable Integer id,
                    @RequestBody DepartmentContribution contribution) {

        return ResponseEntity.ok(
                contributionService.updateContribution(
                        id, contribution
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContribution(
            @PathVariable Integer id) {

        contributionService.deleteContribution(id);

        return ResponseEntity.noContent().build();
    }
}