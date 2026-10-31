package com.faculty.faculty_kpi_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.faculty.faculty_kpi_backend.entity.KpiWeight;
import com.faculty.faculty_kpi_backend.repository.KpiWeightRepository;

@Service
public class KpiWeightService {

    private final KpiWeightRepository kpiWeightRepository;

    public KpiWeightService(KpiWeightRepository kpiWeightRepository) {
        this.kpiWeightRepository = kpiWeightRepository;
    }

    public List<KpiWeight> getAllKpiWeights() {
        return kpiWeightRepository.findAll();
    }

    public KpiWeight getKpiWeightById(Integer id) {
        return kpiWeightRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "KPI weight not found with ID: " + id
                    )
                );
    }

    public KpiWeight addKpiWeight(KpiWeight kpiWeight) {
        return kpiWeightRepository.save(kpiWeight);
    }

    public KpiWeight updateKpiWeight(
            Integer id,
            KpiWeight kpiWeight) {

        KpiWeight existing =
                kpiWeightRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "KPI weight not found with ID: " + id
                    )
                );

        existing.setKpiName(kpiWeight.getKpiName());
        existing.setWeight(kpiWeight.getWeight());
        existing.setMaxScore(kpiWeight.getMaxScore());

        return kpiWeightRepository.save(existing);
    }

    public void deleteKpiWeight(Integer id) {

        if (!kpiWeightRepository.existsById(id)) {
            throw new RuntimeException(
                "KPI weight not found with ID: " + id
            );
        }

        kpiWeightRepository.deleteById(id);
    }
}