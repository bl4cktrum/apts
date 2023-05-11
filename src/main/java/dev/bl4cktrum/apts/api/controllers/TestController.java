package dev.bl4cktrum.apts.api.controllers;

import dev.bl4cktrum.apts.api.models.requests.LoginRequest;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/test")
@Tag(name = "Tests", description = "Includes endpoints to test some functionalities")
public class TestController {
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/auth-needed")
    public ResponseEntity<String> test(){
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return ResponseEntity.ok(SecurityContextHolder.getContext().toString());
    }

    @GetMapping("/auth-not-needed")
    public ResponseEntity<String> testTwo(){
        return ResponseEntity.ok("hello not authenticated world.");
    }

    @PostMapping("/polygon-create")
    public ResponseEntity<String> polygonCreate(@Valid @RequestBody LoginRequest request){
        System.out.println(request.getEmail()+","+request.getPassword());
        return ResponseEntity.ok("oksun ok");
    }
}
