package dev.bl4cktrum.apts.api.controllers;

import dev.bl4cktrum.apts.api.models.dtos.PatientRelevantWithRestrictionsDto;
import dev.bl4cktrum.apts.api.models.responses.GetPatientRelevantsResponse;
import dev.bl4cktrum.apts.api.models.dtos.PatientRelevantDto;
import dev.bl4cktrum.apts.api.services.RelevantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/relevant")
@RequiredArgsConstructor
@Tag(name = "Relevant", description = "Includes endpoints about relevant")
@SecurityRequirement(name = "bearerAuth")
public class RelevantController {
    private final RelevantService relevantService;
    @GetMapping("/devices")
    @Operation(summary = "Gets relations of logged user")
    public ResponseEntity<GetPatientRelevantsResponse> getDevices(){
        Set<PatientRelevantDto> data = relevantService.getDevices();
        return ResponseEntity.ok(GetPatientRelevantsResponse.builder().patient_relevants(data).build());
    }

    @GetMapping("/patient-restrictions/{patient_relevant_id}")
    @Operation(summary = "Gets restrictions of a relation")
    public ResponseEntity<PatientRelevantWithRestrictionsDto> getPatientRelevantRestrictions(@PathVariable("patient_relevant_id") String id){
        PatientRelevantWithRestrictionsDto data = relevantService.getPatientRelevantRestrictions(id);
        return ResponseEntity.ok(data);
    }
}
