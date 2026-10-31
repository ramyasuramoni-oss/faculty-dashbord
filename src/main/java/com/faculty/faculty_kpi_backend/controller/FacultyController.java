package com.faculty.faculty_kpi_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.faculty.faculty_kpi_backend.entity.Faculty;
import com.faculty.faculty_kpi_backend.service.FacultyService;

@RestController
@RequestMapping("/api/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping
    public ResponseEntity<List<Faculty>> getAllFaculty() {
        return ResponseEntity.ok(
                facultyService.getAllFaculty());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Faculty> getFacultyById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                facultyService.getFacultyById(id));
    }

    @PostMapping
    public ResponseEntity<Faculty> addFaculty(
            @RequestBody Faculty faculty) {

        Faculty savedFaculty =
                facultyService.addFaculty(faculty);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedFaculty);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Faculty> updateFaculty(
            @PathVariable Integer id,
            @RequestBody Faculty faculty) {

        return ResponseEntity.ok(
                facultyService.updateFaculty(id, faculty));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFaculty(
            @PathVariable Integer id) {

        facultyService.deleteFaculty(id);

        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleBadRequest(
            IllegalArgumentException exception) {

        return ResponseEntity
                .badRequest()
                .body(exception.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleNotFound(
            RuntimeException exception) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }
}