package dev.bl4cktrum.apts.api.controllers;

import dev.bl4cktrum.apts.api.models.entities.Patient;
import dev.bl4cktrum.apts.api.models.requests.PatientCreateRequest;
import dev.bl4cktrum.apts.api.services.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/patient")
@RequiredArgsConstructor
@Tag(name = "Patient", description = "Includes endpoints about patient")
public class PatientController {
    private final PatientService patientService;
    @PostMapping()
    @Operation(summary = "Creates a patient")
    public ResponseEntity<Patient> create(@Valid @RequestBody PatientCreateRequest request){
        var data = patientService.createPatient(request);
        return ResponseEntity.ok(data);
    }
}
