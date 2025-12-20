package com.hoyoshills.avcalc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hoyoshills.avcalc.dto.CalcRequest;
import com.hoyoshills.avcalc.dto.CalcResponse;
import com.hoyoshills.avcalc.services.SimulationService;

@RestController
@RequestMapping("/api/sim/")
public class CalcController {

    private final SimulationService simulationService;

    public CalcController(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    @PostMapping("/calculate-actions")
    public ResponseEntity<CalcResponse> getActions(@RequestBody CalcRequest request) {
        return ResponseEntity.ok(simulationService.calculateActions(request));
    }
}
