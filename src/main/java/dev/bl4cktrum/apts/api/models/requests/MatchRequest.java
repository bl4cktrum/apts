package dev.bl4cktrum.apts.api.models.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.UUID;

@Data
public class MatchRequest {
    @NotBlank()
    @UUID
    private String patientDeviceId;

    @NotBlank()
    private String name;
}
