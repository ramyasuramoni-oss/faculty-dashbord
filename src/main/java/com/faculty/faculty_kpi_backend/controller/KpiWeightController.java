package com.faculty.faculty_kpi_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.faculty.faculty_kpi_backend.entity.KpiWeight;
import com.faculty.faculty_kpi_backend.service.KpiWeightService;

@RestController
@RequestMapping("/api/kpi-weights")
public class KpiWeightController {

    private final KpiWeightService kpiWeightService;

    public KpiWeightController(KpiWeightService kpiWeightService) {
        this.kpiWeightService = kpiWeightService;
    }

    @GetMapping
    public ResponseEntity<List<KpiWeight>> getAllKpiWeights() {
        return ResponseEntity.ok(
                kpiWeightService.getAllKpiWeights()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<KpiWeight> getKpiWeightById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                kpiWeightService.getKpiWeightById(id)
        );
    }

    @PostMapping
    public ResponseEntity<KpiWeight> addKpiWeight(
            @RequestBody KpiWeight kpiWeight) {

        KpiWeight saved =
                kpiWeightService.addKpiWeight(kpiWeight);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<KpiWeight> updateKpiWeight(
            @PathVariable Integer id,
            @RequestBody KpiWeight kpiWeight) {

        return ResponseEntity.ok(
                kpiWeightService.updateKpiWeight(
                        id, kpiWeight
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKpiWeight(
            @PathVariable Integer id) {

        kpiWeightService.deleteKpiWeight(id);

        return ResponseEntity.noContent().build();
    }
}