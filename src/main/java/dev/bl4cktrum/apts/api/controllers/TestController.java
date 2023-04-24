package dev.bl4cktrum.apts.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    @GetMapping("/auth-needed")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("hello authenticated world.");
    }


    @GetMapping("/auth-not-needed")
    public ResponseEntity<String> testTwo(){
        return ResponseEntity.ok("hello not authenticated world.");
    }
}
