package com.faculty.faculty_kpi_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.faculty.faculty_kpi_backend.entity.ProfessionalDevelopment;
import com.faculty.faculty_kpi_backend.repository.ProfessionalDevelopmentRepository;

@Service
public class ProfessionalDevelopmentService {

    private final ProfessionalDevelopmentRepository developmentRepository;

    public ProfessionalDevelopmentService(
            ProfessionalDevelopmentRepository developmentRepository) {
        this.developmentRepository = developmentRepository;
    }

    public List<ProfessionalDevelopment> getAllDevelopment() {
        return developmentRepository.findAll();
    }

    public ProfessionalDevelopment getDevelopmentById(Integer id) {
        return developmentRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Professional development not found with ID: " + id
                    )
                );
    }

    public ProfessionalDevelopment addDevelopment(
            ProfessionalDevelopment development) {

        return developmentRepository.save(development);
    }

    public ProfessionalDevelopment updateDevelopment(
            Integer id,
            ProfessionalDevelopment development) {

        ProfessionalDevelopment existing =
                developmentRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Professional development not found with ID: " + id
                    )
                );

        existing.setFaculty(development.getFaculty());
        existing.setAcademicYear(development.getAcademicYear());
        existing.setCertifications(development.getCertifications());
        existing.setWorkshops(development.getWorkshops());
        existing.setFdpAttended(development.getFdpAttended());

        return developmentRepository.save(existing);
    }

    public void deleteDevelopment(Integer id) {

        if (!developmentRepository.existsById(id)) {
            throw new RuntimeException(
                "Professional development not found with ID: " + id
            );
        }

        developmentRepository.deleteById(id);
    }
}