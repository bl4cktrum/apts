package dev.bl4cktrum.apts.api.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class PolygonRestrictionCreateRequest {
    @NotBlank
    private String patient_relevant_id;

    @NotBlank
    private String name;

    @NotNull
    @Size(min = 3)
    private List<List<Double>> points;

    private boolean is_active = true;
}
