package dev.bl4cktrum.apts.api.controllers;

import dev.bl4cktrum.apts.api.models.entities.Relevant;
import dev.bl4cktrum.apts.api.models.requests.UserCreateRequest;
import dev.bl4cktrum.apts.api.services.RelevantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    private final RelevantService relevantService;

    public TestController(RelevantService relevantService) {
        this.relevantService = relevantService;
    }

    @PostMapping
    public ResponseEntity<Relevant> createUser(@RequestBody UserCreateRequest userCreateRequest){
        return ResponseEntity.ok(relevantService.createUser(userCreateRequest));
    }
}
