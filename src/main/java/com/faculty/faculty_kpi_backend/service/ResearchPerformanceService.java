package com.faculty.faculty_kpi_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.faculty.faculty_kpi_backend.entity.ResearchPerformance;
import com.faculty.faculty_kpi_backend.repository.ResearchPerformanceRepository;

@Service
public class ResearchPerformanceService {

    private final ResearchPerformanceRepository researchRepository;

    public ResearchPerformanceService(
            ResearchPerformanceRepository researchRepository) {
        this.researchRepository = researchRepository;
    }

    public List<ResearchPerformance> getAllResearchPerformance() {
        return researchRepository.findAll();
    }

    public ResearchPerformance getResearchPerformanceById(Integer id) {
        return researchRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Research performance not found with ID: " + id
                    )
                );
    }

    public ResearchPerformance addResearchPerformance(
            ResearchPerformance researchPerformance) {

        return researchRepository.save(researchPerformance);
    }

    public ResearchPerformance updateResearchPerformance(
            Integer id,
            ResearchPerformance researchPerformance) {

        ResearchPerformance existing =
                researchRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Research performance not found with ID: " + id
                    )
                );

        existing.setFaculty(researchPerformance.getFaculty());
        existing.setAcademicYear(
                researchPerformance.getAcademicYear()
        );
        existing.setPublications(
                researchPerformance.getPublications()
        );
        existing.setConferences(
                researchPerformance.getConferences()
        );
        existing.setPatents(
                researchPerformance.getPatents()
        );
        existing.setResearchProjects(
                researchPerformance.getResearchProjects()
        );
        existing.setBooks(
                researchPerformance.getBooks()
        );

        return researchRepository.save(existing);
    }

    public void deleteResearchPerformance(Integer id) {

        if (!researchRepository.existsById(id)) {
            throw new RuntimeException(
                "Research performance not found with ID: " + id
            );
        }

        researchRepository.deleteById(id);
    }
}