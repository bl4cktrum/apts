package dev.bl4cktrum.apts.api.controllers;

import dev.bl4cktrum.apts.api.models.responses.PatientMatchResponse;
import dev.bl4cktrum.apts.api.models.requests.MatchRequest;
import dev.bl4cktrum.apts.api.services.PatientService;
import dev.bl4cktrum.apts.api.services.RelevantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    private final RelevantService relevantService;
    private final PatientService patientService;

    @PostMapping("/match")
    @Operation(summary = "Matches a patient with a relevant (user)")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<PatientMatchResponse> match(@Valid @RequestBody MatchRequest request){
        PatientMatchResponse data = relevantService.match(request);
        return ResponseEntity.ok(data);
    }
}
