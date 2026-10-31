package com.faculty.faculty_kpi_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.faculty.faculty_kpi_backend.entity.TeachingPerformance;
import com.faculty.faculty_kpi_backend.repository.TeachingPerformanceRepository;

@Service
public class TeachingPerformanceService {

    private final TeachingPerformanceRepository teachingRepository;

    public TeachingPerformanceService(
            TeachingPerformanceRepository teachingRepository) {
        this.teachingRepository = teachingRepository;
    }

    public List<TeachingPerformance> getAllTeachingPerformance() {
        return teachingRepository.findAll();
    }

    public TeachingPerformance getTeachingPerformanceById(Integer id) {
        return teachingRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Teaching performance not found with ID: " + id
                    )
                );
    }

    public TeachingPerformance addTeachingPerformance(
            TeachingPerformance teachingPerformance) {

        return teachingRepository.save(teachingPerformance);
    }

    public TeachingPerformance updateTeachingPerformance(
            Integer id,
            TeachingPerformance teachingPerformance) {

        TeachingPerformance existing =
                teachingRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Teaching performance not found with ID: " + id
                    )
                );

        existing.setFaculty(teachingPerformance.getFaculty());
        existing.setAcademicYear(
                teachingPerformance.getAcademicYear()
        );
        existing.setClassesAssigned(
                teachingPerformance.getClassesAssigned()
        );
        existing.setClassesConducted(
                teachingPerformance.getClassesConducted()
        );
        existing.setStudentsTotal(
                teachingPerformance.getStudentsTotal()
        );
        existing.setStudentsPassed(
                teachingPerformance.getStudentsPassed()
        );
        existing.setAttendancePercentage(
                teachingPerformance.getAttendancePercentage()
        );

        return teachingRepository.save(existing);
    }

    public void deleteTeachingPerformance(Integer id) {

        if (!teachingRepository.existsById(id)) {
            throw new RuntimeException(
                "Teaching performance not found with ID: " + id
            );
        }

        teachingRepository.deleteById(id);
    }
}