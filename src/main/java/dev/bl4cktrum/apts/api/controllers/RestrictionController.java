package dev.bl4cktrum.apts.api.controllers;

import dev.bl4cktrum.apts.api.models.requests.CircleRestrictionCreateRequest;
import dev.bl4cktrum.apts.api.models.requests.PolygonRestrictionCreateRequest;
import dev.bl4cktrum.apts.api.models.responses.ApiResponse;
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
public class RestrictionController {
    private final RestrictionService restrictionService;

    @PostMapping("/polygon")
    @Operation(summary = "Creates a restriction")
    public ResponseEntity<ApiResponse> polygonCreate(@Valid @RequestBody PolygonRestrictionCreateRequest request){
        restrictionService.polygonRestrictionCreate(request);
        return ResponseEntity.ok(ApiResponse.builder().message("").build());
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/circle")
    @Operation(summary = "Creates a restriction")
    public ResponseEntity<ApiResponse> circleCreate(@Valid @RequestBody CircleRestrictionCreateRequest request){
        restrictionService.circleRestrictionCreate(request);
        return ResponseEntity.ok(ApiResponse.builder().message("Restriction had been created successfully").build());
    }
}
