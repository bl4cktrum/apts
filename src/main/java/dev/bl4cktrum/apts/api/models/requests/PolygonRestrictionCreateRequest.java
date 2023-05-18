package dev.bl4cktrum.apts.api.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class PolygonRestrictionCreateRequest {
    @NotBlank
    private String patient_id;

    @NotBlank
    private String name;

    @NotBlank
    @NotNull
    private List<List<Integer>> points;

    private boolean is_active = true;
}