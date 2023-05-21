package dev.bl4cktrum.apts.api.controllers;

import dev.bl4cktrum.apts.api.models.requests.CircleRestrictionCreateRequest;
import dev.bl4cktrum.apts.api.models.requests.PolygonRestrictionCreateRequest;
import dev.bl4cktrum.apts.api.services.RestrictionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/restriction")
@RequiredArgsConstructor
@Tag(name = "Restriction", description = "Includes endpoints about Restriction")
@SecurityRequirement(name = "bearerAuth")
public class RestrictionController {
    private final RestrictionService restrictionService;

    @PostMapping("/polygon")
    @Operation(summary = "Creates a restriction")
    public ResponseEntity<String> polygonCreate(@Valid @RequestBody PolygonRestrictionCreateRequest request){
        restrictionService.polygonRestrictionCreate(request);
        return ResponseEntity.ok("Created Successfully");
    }


    @PostMapping("/circle")
    @Operation(summary = "Creates a restriction")
    public ResponseEntity<String> circleCreate(@Valid @RequestBody CircleRestrictionCreateRequest request){
        restrictionService.circleRestrictionCreate(request);
        return ResponseEntity.ok("Restriction had been created successfully");
    }

    @PutMapping("/{restriction_id}/activation")
    @Operation(summary = "Updates activation state of a restriction")
    public ResponseEntity<String> activationUpdate(
            @PathVariable(name = "restriction_id") String restrictionId,
            @RequestParam boolean isActive){
        restrictionService.activationUpdate(restrictionId, isActive);
        return ResponseEntity.ok("Activation had been updated successfully");
    }
}
