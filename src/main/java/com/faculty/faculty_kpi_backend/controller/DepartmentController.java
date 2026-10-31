package com.faculty.faculty_kpi_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.faculty.faculty_kpi_backend.entity.Department;
import com.faculty.faculty_kpi_backend.service.DepartmentService;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // GET all departments
    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        return ResponseEntity.ok(
                departmentService.getAllDepartments()
        );
    }

    // GET department by ID
    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                departmentService.getDepartmentById(id)
        );
    }

    // POST - Add department
    @PostMapping
    public ResponseEntity<Department> addDepartment(
            @RequestBody Department department) {

        Department savedDepartment =
                departmentService.addDepartment(department);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedDepartment);
    }

    // PUT - Update department
    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(
            @PathVariable Integer id,
            @RequestBody Department department) {

        return ResponseEntity.ok(
                departmentService.updateDepartment(id, department)
        );
    }

    // DELETE - Delete department
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(
            @PathVariable Integer id) {

        departmentService.deleteDepartment(id);

        return ResponseEntity.noContent().build();
    }
}
