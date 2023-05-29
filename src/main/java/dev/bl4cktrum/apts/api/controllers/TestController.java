package dev.bl4cktrum.apts.api.controllers;

import com.corundumstudio.socketio.SocketIOServer;
import dev.bl4cktrum.apts.api.models.requests.LoginRequest;
import dev.bl4cktrum.apts.socketIO.models.SendLocationModel;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/test")
@Tag(name = "Tests", description = "Includes endpoints to test some functionalities")
@RequiredArgsConstructor
public class TestController {
    private final SocketIOServer socketIOServer;

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

    @PostMapping("/test-curr")
    public ResponseEntity<String> testCurr(){;
        socketIOServer.getAllClients().forEach(socketIOClient -> {
            for (int i = 0; i < 50; i++) {
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                socketIOClient.sendEvent("current_location",SendLocationModel.builder().latitude(23.76d+(i/5000d)).longitude(90.42d+(i/5000d)).build());
            }
        });
        return ResponseEntity.ok("ok");
    }
}
