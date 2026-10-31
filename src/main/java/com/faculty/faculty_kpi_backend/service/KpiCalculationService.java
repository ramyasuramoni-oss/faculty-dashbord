package com.faculty.faculty_kpi_backend.service;

import java.math.BigDecimal;
import com.faculty.faculty_kpi_backend.entity.ProfessionalDevelopment;
import com.faculty.faculty_kpi_backend.repository.ProfessionalDevelopmentRepository;
import com.faculty.faculty_kpi_backend.entity.StudentFeedback;
import com.faculty.faculty_kpi_backend.repository.StudentFeedbackRepository;
import java.math.RoundingMode;
import com.faculty.faculty_kpi_backend.entity.DepartmentContribution;
import com.faculty.faculty_kpi_backend.repository.DepartmentContributionRepository;

import org.springframework.stereotype.Service;

import com.faculty.faculty_kpi_backend.entity.TeachingPerformance;
import com.faculty.faculty_kpi_backend.repository.TeachingPerformanceRepository;

import com.faculty.faculty_kpi_backend.entity.ResearchPerformance;
import com.faculty.faculty_kpi_backend.repository.ResearchPerformanceRepository;

import com.faculty.faculty_kpi_backend.repository.FacultyRepository;
import com.faculty.faculty_kpi_backend.dto.FacultyKpiResponse;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class KpiCalculationService {

    private final TeachingPerformanceRepository teachingRepository;
    private final ResearchPerformanceRepository researchRepository;
    private final StudentFeedbackRepository feedbackRepository;
    private final ProfessionalDevelopmentRepository developmentRepository;
    private final DepartmentContributionRepository contributionRepository;
    private final FacultyRepository facultyRepository;
    
    public KpiCalculationService(
            TeachingPerformanceRepository teachingRepository,
            ResearchPerformanceRepository researchRepository,
            StudentFeedbackRepository feedbackRepository,
            ProfessionalDevelopmentRepository developmentRepository,
            DepartmentContributionRepository contributionRepository,
            FacultyRepository facultyRepository){
        this.teachingRepository = teachingRepository;
        this.researchRepository = researchRepository;
        this.feedbackRepository = feedbackRepository;
        this.developmentRepository = developmentRepository;
        this.contributionRepository = contributionRepository;
        this.facultyRepository = facultyRepository;
    }

    public BigDecimal calculateTeachingScore(Integer facultyId) {

        TeachingPerformance teaching =
                teachingRepository.findAll()
                        .stream()
                        .filter(t ->
                            t.getFaculty()
                             .getFacultyId()
                             .equals(facultyId)
                        )
                        .findFirst()
                        .orElseThrow(() ->
                            new RuntimeException(
                                "Teaching performance not found for faculty ID: "
                                + facultyId
                            )
                        );

        // 1. Class completion percentage
        BigDecimal classCompletion =
                BigDecimal.valueOf(
                    ((double) teaching.getClassesConducted()
                    / teaching.getClassesAssigned()) * 100
                );

        // 2. Student pass percentage
        BigDecimal passPercentage =
                BigDecimal.valueOf(
                    ((double) teaching.getStudentsPassed()
                    / teaching.getStudentsTotal()) * 100
                );

        // 3. Teaching score
        BigDecimal score =
                classCompletion.multiply(
                    BigDecimal.valueOf(0.40)
                )
                .add(
                    teaching.getAttendancePercentage()
                        .multiply(BigDecimal.valueOf(0.30))
                )
                .add(
                    passPercentage.multiply(
                        BigDecimal.valueOf(0.30)
                    )
                );

        return score.setScale(2, RoundingMode.HALF_UP);
    }
    
    public BigDecimal calculateResearchScore(Integer facultyId) {

        ResearchPerformance research =
                researchRepository.findAll()
                        .stream()
                        .filter(r ->
                            r.getFaculty()
                             .getFacultyId()
                             .equals(facultyId)
                        )
                        .findFirst()
                        .orElseThrow(() ->
                            new RuntimeException(
                                "Research performance not found for faculty ID: "
                                + facultyId
                            )
                        );

        // Normalize each research component to a score out of 100

        BigDecimal publicationScore =
                BigDecimal.valueOf(research.getPublications())
                        .divide(BigDecimal.valueOf(5), 4, RoundingMode.HALF_UP)
                        .multiply(BigDecimal.valueOf(100));

        BigDecimal conferenceScore =
                BigDecimal.valueOf(research.getConferences())
                        .divide(BigDecimal.valueOf(4), 4, RoundingMode.HALF_UP)
                        .multiply(BigDecimal.valueOf(100));

        BigDecimal patentScore =
                BigDecimal.valueOf(research.getPatents())
                        .divide(BigDecimal.valueOf(2), 4, RoundingMode.HALF_UP)
                        .multiply(BigDecimal.valueOf(100));

        BigDecimal projectScore =
                BigDecimal.valueOf(research.getResearchProjects())
                        .divide(BigDecimal.valueOf(3), 4, RoundingMode.HALF_UP)
                        .multiply(BigDecimal.valueOf(100));

        BigDecimal bookScore =
                BigDecimal.valueOf(research.getBooks())
                        .divide(BigDecimal.valueOf(2), 4, RoundingMode.HALF_UP)
                        .multiply(BigDecimal.valueOf(100));

        // Weighted Research Score

        BigDecimal researchScore =
                publicationScore.multiply(BigDecimal.valueOf(0.30))
                .add(
                    conferenceScore.multiply(
                        BigDecimal.valueOf(0.15)
                    )
                )
                .add(
                    patentScore.multiply(
                        BigDecimal.valueOf(0.25)
                    )
                )
                .add(
                    projectScore.multiply(
                        BigDecimal.valueOf(0.20)
                    )
                )
                .add(
                    bookScore.multiply(
                        BigDecimal.valueOf(0.10)
                    )
                );

        // Maximum score is capped at 100

        if (researchScore.compareTo(BigDecimal.valueOf(100)) > 0) {
            researchScore = BigDecimal.valueOf(100);
        }

        return researchScore.setScale(
                2,
                RoundingMode.HALF_UP
        );
    }
    public BigDecimal calculateFeedbackScore(Integer facultyId) {

        StudentFeedback feedback =
                feedbackRepository.findAll()
                        .stream()
                        .filter(f ->
                            f.getFaculty()
                             .getFacultyId()
                             .equals(facultyId)
                        )
                        .findFirst()
                        .orElseThrow(() ->
                            new RuntimeException(
                                "Student feedback not found for faculty ID: "
                                + facultyId
                            )
                        );

        BigDecimal feedbackScore =
                feedback.getRating()
                        .divide(
                            BigDecimal.valueOf(5),
                            4,
                            RoundingMode.HALF_UP
                        )
                        .multiply(BigDecimal.valueOf(100));

        return feedbackScore.setScale(
                2,
                RoundingMode.HALF_UP
        );
    }public BigDecimal calculateProfessionalScore(Integer facultyId) {

        ProfessionalDevelopment development =
                developmentRepository.findAll()
                        .stream()
                        .filter(d ->
                            d.getFaculty()
                             .getFacultyId()
                             .equals(facultyId)
                        )
                        .findFirst()
                        .orElseThrow(() ->
                            new RuntimeException(
                                "Professional development not found for faculty ID: "
                                + facultyId
                            )
                        );

        BigDecimal certificationScore =
                BigDecimal.valueOf(development.getCertifications())
                        .divide(
                            BigDecimal.valueOf(3),
                            4,
                            RoundingMode.HALF_UP
                        )
                        .multiply(BigDecimal.valueOf(100));

        BigDecimal workshopScore =
                BigDecimal.valueOf(development.getWorkshops())
                        .divide(
                            BigDecimal.valueOf(5),
                            4,
                            RoundingMode.HALF_UP
                        )
                        .multiply(BigDecimal.valueOf(100));

        BigDecimal fdpScore =
                BigDecimal.valueOf(development.getFdpAttended())
                        .divide(
                            BigDecimal.valueOf(5),
                            4,
                            RoundingMode.HALF_UP
                        )
                        .multiply(BigDecimal.valueOf(100));

        BigDecimal professionalScore =
                certificationScore.multiply(
                        BigDecimal.valueOf(0.40)
                )
                .add(
                    workshopScore.multiply(
                        BigDecimal.valueOf(0.30)
                    )
                )
                .add(
                    fdpScore.multiply(
                        BigDecimal.valueOf(0.30)
                    )
                );

        if (professionalScore.compareTo(
                BigDecimal.valueOf(100)) > 0) {

            professionalScore = BigDecimal.valueOf(100);
        }

        return professionalScore.setScale(
                2,
                RoundingMode.HALF_UP
        );
    }
    public BigDecimal calculateContributionScore(Integer facultyId) {

        DepartmentContribution contribution =
                contributionRepository.findAll()
                        .stream()
                        .filter(c ->
                            c.getFaculty()
                             .getFacultyId()
                             .equals(facultyId)
                        )
                        .findFirst()
                        .orElseThrow(() ->
                            new RuntimeException(
                                "Department contribution not found for faculty ID: "
                                + facultyId
                            )
                        );

        BigDecimal committeeScore =
                BigDecimal.valueOf(contribution.getCommitteeWork())
                        .divide(
                            BigDecimal.valueOf(5),
                            4,
                            RoundingMode.HALF_UP
                        )
                        .multiply(BigDecimal.valueOf(100));

        BigDecimal eventScore =
                BigDecimal.valueOf(contribution.getEventsConducted())
                        .divide(
                            BigDecimal.valueOf(5),
                            4,
                            RoundingMode.HALF_UP
                        )
                        .multiply(BigDecimal.valueOf(100));

        BigDecimal mentoringScore =
                BigDecimal.valueOf(
                        contribution.getMentoringActivities()
                )
                        .divide(
                            BigDecimal.valueOf(10),
                            4,
                            RoundingMode.HALF_UP
                        )
                        .multiply(BigDecimal.valueOf(100));

        BigDecimal contributionScore =
                committeeScore.multiply(
                        BigDecimal.valueOf(0.30)
                )
                .add(
                    eventScore.multiply(
                        BigDecimal.valueOf(0.30)
                    )
                )
                .add(
                    mentoringScore.multiply(
                        BigDecimal.valueOf(0.40)
                    )
                );

        if (contributionScore.compareTo(
                BigDecimal.valueOf(100)) > 0) {

            contributionScore = BigDecimal.valueOf(100);
        }

        return contributionScore.setScale(
                2,
                RoundingMode.HALF_UP
        );
    }
    
 // Overall KPI Score
    public BigDecimal calculateOverallKpi(Integer facultyId) {

        BigDecimal teachingScore = calculateTeachingScore(facultyId);
        BigDecimal researchScore = calculateResearchScore(facultyId);
        BigDecimal feedbackScore = calculateFeedbackScore(facultyId);
        BigDecimal professionalScore = calculateProfessionalScore(facultyId);
        BigDecimal contributionScore = calculateContributionScore(facultyId);

        BigDecimal overallScore =
                teachingScore.multiply(BigDecimal.valueOf(0.40))
                .add(
                    researchScore.multiply(BigDecimal.valueOf(0.25))
                )
                .add(
                    feedbackScore.multiply(BigDecimal.valueOf(0.15))
                )
                .add(
                    professionalScore.multiply(BigDecimal.valueOf(0.10))
                )
                .add(
                    contributionScore.multiply(BigDecimal.valueOf(0.10))
                );

        return overallScore.setScale(
                2,
                RoundingMode.HALF_UP
        );
    }
    
    public FacultyKpiResponse getFacultyKpiSummary(Integer facultyId) {

        var faculty = facultyRepository.findById(facultyId)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Faculty not found with ID: " + facultyId
                    )
                );

        BigDecimal teachingScore = calculateTeachingScore(facultyId);
        BigDecimal researchScore = calculateResearchScore(facultyId);
        BigDecimal feedbackScore = calculateFeedbackScore(facultyId);
        BigDecimal professionalScore = calculateProfessionalScore(facultyId);
        BigDecimal contributionScore = calculateContributionScore(facultyId);
        BigDecimal overallScore = calculateOverallKpi(facultyId);

        return new FacultyKpiResponse(
                faculty.getFacultyId(),
                faculty.getFacultyName(),
                teachingScore,
                researchScore,
                feedbackScore,
                professionalScore,
                contributionScore,
                overallScore
        );
    }
    
    public List<FacultyKpiResponse> getAllFacultyKpiSummary() {

        return facultyRepository.findAll()
                .stream()
                .map(faculty ->
                    getFacultyKpiSummary(faculty.getFacultyId())
                )
                .collect(Collectors.toList());
    }
}