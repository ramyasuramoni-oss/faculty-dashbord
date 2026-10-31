package com.faculty.faculty_kpi_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "professional_development")
public class ProfessionalDevelopment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "development_id")
    private Integer developmentId;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    @Column(name = "academic_year")
    private String academicYear;

    @Column(name = "certifications")
    private Integer certifications;

    @Column(name = "workshops")
    private Integer workshops;

    @Column(name = "fdp_attended")
    private Integer fdpAttended;

    public ProfessionalDevelopment() {
    }

    public Integer getDevelopmentId() {
        return developmentId;
    }

    public void setDevelopmentId(Integer developmentId) {
        this.developmentId = developmentId;
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

    public Integer getCertifications() {
        return certifications;
    }

    public void setCertifications(Integer certifications) {
        this.certifications = certifications;
    }

    public Integer getWorkshops() {
        return workshops;
    }

    public void setWorkshops(Integer workshops) {
        this.workshops = workshops;
    }

    public Integer getFdpAttended() {
        return fdpAttended;
    }

    public void setFdpAttended(Integer fdpAttended) {
        this.fdpAttended = fdpAttended;
    }
}