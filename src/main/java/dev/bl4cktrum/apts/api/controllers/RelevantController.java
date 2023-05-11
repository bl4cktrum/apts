package dev.bl4cktrum.apts.api.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/relevant")
@RequiredArgsConstructor
@Tag(name = "Relevant", description = "Includes endpoints about relevant")
public class RelevantController {

}
