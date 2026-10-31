package com.faculty.faculty_kpi_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "research_performance")
public class ResearchPerformance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "research_id")
    private Integer researchId;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    @Column(name = "academic_year")
    private String academicYear;

    @Column(name = "publications")
    private Integer publications;

    @Column(name = "conferences")
    private Integer conferences;

    @Column(name = "patents")
    private Integer patents;

    @Column(name = "research_projects")
    private Integer researchProjects;

    @Column(name = "books")
    private Integer books;

    public ResearchPerformance() {
    }

    public Integer getResearchId() {
        return researchId;
    }

    public void setResearchId(Integer researchId) {
        this.researchId = researchId;
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

    public Integer getPublications() {
        return publications;
    }

    public void setPublications(Integer publications) {
        this.publications = publications;
    }

    public Integer getConferences() {
        return conferences;
    }

    public void setConferences(Integer conferences) {
        this.conferences = conferences;
    }

    public Integer getPatents() {
        return patents;
    }

    public void setPatents(Integer patents) {
        this.patents = patents;
    }

    public Integer getResearchProjects() {
        return researchProjects;
    }

    public void setResearchProjects(Integer researchProjects) {
        this.researchProjects = researchProjects;
    }

    public Integer getBooks() {
        return books;
    }

    public void setBooks(Integer books) {
        this.books = books;
    }
}