package dev.bl4cktrum.apts.api.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class CircleRestrictionCreateRequest {
    @NotBlank
    private String patient_id;

    @NotBlank
    private String name;

    @NotNull
    @Size(min = 2,max = 2)
    private List<Float> center;

    @NotNull
    private float radius;

    private boolean is_active = true;
}
