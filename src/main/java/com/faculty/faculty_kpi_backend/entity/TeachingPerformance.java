package com.faculty.faculty_kpi_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "teaching_performance")
public class TeachingPerformance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teaching_id")
    private Integer teachingId;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    @Column(name = "academic_year")
    private String academicYear;

    @Column(name = "classes_assigned")
    private Integer classesAssigned;

    @Column(name = "classes_conducted")
    private Integer classesConducted;

    @Column(name = "students_total")
    private Integer studentsTotal;

    @Column(name = "students_passed")
    private Integer studentsPassed;

    @Column(name = "attendance_percentage")
    private java.math.BigDecimal attendancePercentage;

    public TeachingPerformance() {
    }

    public Integer getTeachingId() {
        return teachingId;
    }

    public void setTeachingId(Integer teachingId) {
        this.teachingId = teachingId;
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

    public Integer getClassesAssigned() {
        return classesAssigned;
    }

    public void setClassesAssigned(Integer classesAssigned) {
        this.classesAssigned = classesAssigned;
    }

    public Integer getClassesConducted() {
        return classesConducted;
    }

    public void setClassesConducted(Integer classesConducted) {
        this.classesConducted = classesConducted;
    }

    public Integer getStudentsTotal() {
        return studentsTotal;
    }

    public void setStudentsTotal(Integer studentsTotal) {
        this.studentsTotal = studentsTotal;
    }

    public Integer getStudentsPassed() {
        return studentsPassed;
    }

    public void setStudentsPassed(Integer studentsPassed) {
        this.studentsPassed = studentsPassed;
    }

    public java.math.BigDecimal getAttendancePercentage() {
        return attendancePercentage;
    }

    public void setAttendancePercentage(java.math.BigDecimal attendancePercentage) {
        this.attendancePercentage = attendancePercentage;
    }
}