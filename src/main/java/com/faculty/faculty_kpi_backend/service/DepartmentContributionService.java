package com.faculty.faculty_kpi_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.faculty.faculty_kpi_backend.entity.DepartmentContribution;
import com.faculty.faculty_kpi_backend.repository.DepartmentContributionRepository;

@Service
public class DepartmentContributionService {

    private final DepartmentContributionRepository contributionRepository;

    public DepartmentContributionService(
            DepartmentContributionRepository contributionRepository) {
        this.contributionRepository = contributionRepository;
    }

    public List<DepartmentContribution> getAllContributions() {
        return contributionRepository.findAll();
    }

    public DepartmentContribution getContributionById(Integer id) {
        return contributionRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Department contribution not found with ID: " + id
                    )
                );
    }

    public DepartmentContribution addContribution(
            DepartmentContribution contribution) {

        return contributionRepository.save(contribution);
    }

    public DepartmentContribution updateContribution(
            Integer id,
            DepartmentContribution contribution) {

        DepartmentContribution existing =
                contributionRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Department contribution not found with ID: " + id
                    )
                );

        existing.setFaculty(contribution.getFaculty());
        existing.setAcademicYear(contribution.getAcademicYear());
        existing.setCommitteeWork(contribution.getCommitteeWork());
        existing.setEventsConducted(contribution.getEventsConducted());
        existing.setMentoringActivities(
                contribution.getMentoringActivities()
        );

        return contributionRepository.save(existing);
    }

    public void deleteContribution(Integer id) {

        if (!contributionRepository.existsById(id)) {
            throw new RuntimeException(
                "Department contribution not found with ID: " + id
            );
        }

        contributionRepository.deleteById(id);
    }
}