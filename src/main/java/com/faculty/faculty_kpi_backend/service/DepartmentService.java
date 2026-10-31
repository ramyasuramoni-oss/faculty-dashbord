package com.faculty.faculty_kpi_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.faculty.faculty_kpi_backend.entity.Department;
import com.faculty.faculty_kpi_backend.repository.DepartmentRepository;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    // Get all departments
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    // Get department by ID
    public Department getDepartmentById(Integer id) {
        return departmentRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException("Department not found with ID: " + id)
                );
    }

    // Add department
    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    // Update department
    public Department updateDepartment(Integer id, Department department) {

        Department existingDepartment =
                departmentRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException("Department not found with ID: " + id)
                );

        existingDepartment.setDepartmentName(
                department.getDepartmentName()
        );

        return departmentRepository.save(existingDepartment);
    }

    // Delete department
    public void deleteDepartment(Integer id) {

        if (!departmentRepository.existsById(id)) {
            throw new RuntimeException(
                "Department not found with ID: " + id
            );
        }

        departmentRepository.deleteById(id);
    }
}
