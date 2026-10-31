package com.faculty.faculty_kpi_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.faculty.faculty_kpi_backend.entity.ProfessionalDevelopment;
import com.faculty.faculty_kpi_backend.service.ProfessionalDevelopmentService;

@RestController
@RequestMapping("/api/professional-development")
public class ProfessionalDevelopmentController {

    private final ProfessionalDevelopmentService developmentService;

    public ProfessionalDevelopmentController(
            ProfessionalDevelopmentService developmentService) {
        this.developmentService = developmentService;
    }

    @GetMapping
    public ResponseEntity<List<ProfessionalDevelopment>>
            getAllDevelopment() {

        return ResponseEntity.ok(
                developmentService.getAllDevelopment()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessionalDevelopment>
            getDevelopmentById(
                    @PathVariable Integer id) {

        return ResponseEntity.ok(
                developmentService.getDevelopmentById(id)
        );
    }

    @PostMapping
    public ResponseEntity<ProfessionalDevelopment>
            addDevelopment(
                    @RequestBody ProfessionalDevelopment development) {

        ProfessionalDevelopment saved =
                developmentService.addDevelopment(development);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessionalDevelopment>
            updateDevelopment(
                    @PathVariable Integer id,
                    @RequestBody ProfessionalDevelopment development) {

        return ResponseEntity.ok(
                developmentService.updateDevelopment(
                        id, development
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevelopment(
            @PathVariable Integer id) {

        developmentService.deleteDevelopment(id);

        return ResponseEntity.noContent().build();
    }
}