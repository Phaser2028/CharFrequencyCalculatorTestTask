package com.example.demo.controller;

import com.example.demo.service.CalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {

    private final CalculationService calculationService;
    @Autowired
    public DataController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @PostMapping("/calc")
    public ResponseEntity<?> getCalculation(@RequestBody String inputData){
        return ResponseEntity.ok(calculationService.calculate(inputData));
    }

}
