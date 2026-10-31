package com.faculty.faculty_kpi_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.faculty.faculty_kpi_backend.entity.Department;
import com.faculty.faculty_kpi_backend.entity.Faculty;
import com.faculty.faculty_kpi_backend.repository.DepartmentRepository;
import com.faculty.faculty_kpi_backend.repository.FacultyRepository;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;
    private final DepartmentRepository departmentRepository;

    public FacultyService(
            FacultyRepository facultyRepository,
            DepartmentRepository departmentRepository) {

        this.facultyRepository = facultyRepository;
        this.departmentRepository = departmentRepository;
    }

    public List<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }

    public Faculty getFacultyById(Integer id) {
        return facultyRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Faculty not found with ID: " + id));
    }

    @Transactional
    public Faculty addFaculty(Faculty faculty) {

        faculty.setFacultyId(null);

        if (faculty.getFacultyName() == null
                || faculty.getFacultyName().isBlank()) {
            throw new IllegalArgumentException(
                    "Faculty name is required");
        }

        if (faculty.getEmail() == null
                || faculty.getEmail().isBlank()) {
            throw new IllegalArgumentException(
                    "Email is required");
        }

        if (faculty.getDesignation() == null
                || faculty.getDesignation().isBlank()) {
            throw new IllegalArgumentException(
                    "Designation is required");
        }

        if (faculty.getDepartment() == null
                || faculty.getDepartment().getDepartmentId() == null) {
            throw new IllegalArgumentException(
                    "Department is required");
        }

        Department department = departmentRepository
                .findById(
                        faculty.getDepartment().getDepartmentId())
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Selected department does not exist"));

        faculty.setDepartment(department);

        faculty.setFacultyName(
                faculty.getFacultyName().trim());

        faculty.setEmail(
                faculty.getEmail().trim());

        return facultyRepository.save(faculty);
    }

    @Transactional
    public Faculty updateFaculty(Integer id, Faculty updatedFaculty) {

        Faculty existing = getFacultyById(id);

        existing.setFacultyName(
                updatedFaculty.getFacultyName());

        existing.setEmail(
                updatedFaculty.getEmail());

        existing.setPhone(
                updatedFaculty.getPhone());

        existing.setDesignation(
                updatedFaculty.getDesignation());

        existing.setJoiningDate(
                updatedFaculty.getJoiningDate());

        if (updatedFaculty.getDepartment() != null
                && updatedFaculty.getDepartment().getDepartmentId() != null) {

            Department department = departmentRepository
                    .findById(
                            updatedFaculty.getDepartment()
                                    .getDepartmentId())
                    .orElseThrow(() ->
                            new IllegalArgumentException(
                                    "Department not found"));

            existing.setDepartment(department);
        }

        return facultyRepository.save(existing);
    }

    @Transactional
    public void deleteFaculty(Integer id) {

        Faculty faculty = getFacultyById(id);

        /*
         * Do not automatically delete the faculty member if other
         * KPI tables reference this record.
         *
         * Foreign-key constraints should protect existing performance
         * data until an appropriate archival or deletion policy exists.
         */
        facultyRepository.delete(faculty);
    }
}