package com.faculty.faculty_kpi_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "department_contribution")
public class DepartmentContribution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contribution_id")
    private Integer contributionId;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    @Column(name = "academic_year")
    private String academicYear;

    @Column(name = "committee_work")
    private Integer committeeWork;

    @Column(name = "events_conducted")
    private Integer eventsConducted;

    @Column(name = "mentoring_activities")
    private Integer mentoringActivities;

    public DepartmentContribution() {
    }

    public Integer getContributionId() {
        return contributionId;
    }

    public void setContributionId(Integer contributionId) {
        this.contributionId = contributionId;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public Integer getCommitteeWork() {
        return committeeWork;
    }

    public void setCommitteeWork(Integer committeeWork) {
        this.committeeWork = committeeWork;
    }

    public Integer getEventsConducted() {
        return eventsConducted;
    }

    public void setEventsConducted(Integer eventsConducted) {
        this.eventsConducted = eventsConducted;
    }

    public Integer getMentoringActivities() {
        return mentoringActivities;
    }

    public void setMentoringActivities(Integer mentoringActivities) {
        this.mentoringActivities = mentoringActivities;
    }
}