package com.faculty.faculty_kpi_backend.entity;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "kpi_weights")
public class KpiWeight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kpi_id")
    private Integer kpiId;

    @Column(name = "kpi_name")
    private String kpiName;

    @Column(name = "weight")
    private BigDecimal weight;

    @Column(name = "max_score")
    private BigDecimal maxScore;

    public KpiWeight() {
    }

    public Integer getKpiId() {
        return kpiId;
    }

    public void setKpiId(Integer kpiId) {
        this.kpiId = kpiId;
    }

    public String getKpiName() {
        return kpiName;
    }

    public void setKpiName(String kpiName) {
        this.kpiName = kpiName;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(BigDecimal maxScore) {
        this.maxScore = maxScore;
    }
}