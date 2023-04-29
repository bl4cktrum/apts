package dev.bl4cktrum.apts.api.controllers;

import dev.bl4cktrum.apts.api.exceptions.ApiException;
import dev.bl4cktrum.apts.api.models.requests.LoginRequest;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import net.postgis.jdbc.geometry.Polygon;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/auth-needed")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("hello authenticated world.");
    }


    @GetMapping("/auth-not-needed")
    public ResponseEntity<String> testTwo(){
        return ResponseEntity.ok("hello not authenticated world.");
    }
    @PostMapping("/polygon-create")
    public ResponseEntity<String> polygonCreate(@RequestBody LoginRequest request){
        throw new ApiException("test");
//        try {
//            Polygon p1 = new Polygon("(50.6373 3.0750,50.6374 3.0750,50.6374 3.0749,50.63 3.07491,50.6373 3.0750)");
//            System.out.println(p1.getRing(0));
//            return ResponseEntity.ok(p1.toString());
//        } catch (SQLException e) {
//            System.err.println(e);
//            return null;
//        }
    }
}
