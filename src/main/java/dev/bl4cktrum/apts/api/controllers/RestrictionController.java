package dev.bl4cktrum.apts.api.controllers;

import dev.bl4cktrum.apts.api.models.requests.RestrictionCreateRequest;
import dev.bl4cktrum.apts.api.services.RestrictionService;
import io.swagger.v3.oas.annotations.Operation;
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

    @PostMapping()
    @Operation(summary = "Creates a restriction")
    public ResponseEntity<String> match(@Valid @RequestBody RestrictionCreateRequest request){
        restrictionService.restrictionCreate(request);
        return ResponseEntity.ok("Created successfully");
    }
}
