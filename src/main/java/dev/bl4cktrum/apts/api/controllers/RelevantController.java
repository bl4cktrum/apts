package dev.bl4cktrum.apts.api.controllers;

import dev.bl4cktrum.apts.api.models.requests.MatchRequest;
import dev.bl4cktrum.apts.api.services.RelevantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/relevant")
@RequiredArgsConstructor
@Tag(name = "Relevant", description = "Includes endpoints about relevant")
public class RelevantController {
    private final RelevantService relevantService;
    @PostMapping("/match")
    @Operation(summary = "Matches a patient with a relevant (user)")
    public ResponseEntity<String> match(@Valid @RequestBody MatchRequest request){
        relevantService.match(request);
        return ResponseEntity.ok("Matched successfully");
    }
}
