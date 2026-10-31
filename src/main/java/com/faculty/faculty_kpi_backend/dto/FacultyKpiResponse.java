package com.faculty.faculty_kpi_backend.dto;

import java.math.BigDecimal;

public class FacultyKpiResponse {

    private Integer facultyId;
    private String facultyName;

    private BigDecimal teachingScore;
    private BigDecimal researchScore;
    private BigDecimal feedbackScore;
    private BigDecimal professionalScore;
    private BigDecimal contributionScore;
    private BigDecimal overallScore;

    public FacultyKpiResponse() {
    }

    public FacultyKpiResponse(
            Integer facultyId,
            String facultyName,
            BigDecimal teachingScore,
            BigDecimal researchScore,
            BigDecimal feedbackScore,
            BigDecimal professionalScore,
            BigDecimal contributionScore,
            BigDecimal overallScore) {

        this.facultyId = facultyId;
        this.facultyName = facultyName;
        this.teachingScore = teachingScore;
        this.researchScore = researchScore;
        this.feedbackScore = feedbackScore;
        this.professionalScore = professionalScore;
        this.contributionScore = contributionScore;
        this.overallScore = overallScore;
    }

    public Integer getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Integer facultyId) {
        this.facultyId = facultyId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public BigDecimal getTeachingScore() {
        return teachingScore;
    }

    public void setTeachingScore(BigDecimal teachingScore) {
        this.teachingScore = teachingScore;
    }

    public BigDecimal getResearchScore() {
        return researchScore;
    }

    public void setResearchScore(BigDecimal researchScore) {
        this.researchScore = researchScore;
    }

    public BigDecimal getFeedbackScore() {
        return feedbackScore;
    }

    public void setFeedbackScore(BigDecimal feedbackScore) {
        this.feedbackScore = feedbackScore;
    }

    public BigDecimal getProfessionalScore() {
        return professionalScore;
    }

    public void setProfessionalScore(BigDecimal professionalScore) {
        this.professionalScore = professionalScore;
    }

    public BigDecimal getContributionScore() {
        return contributionScore;
    }

    public void setContributionScore(BigDecimal contributionScore) {
        this.contributionScore = contributionScore;
    }

    public BigDecimal getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(BigDecimal overallScore) {
        this.overallScore = overallScore;
    }
}
