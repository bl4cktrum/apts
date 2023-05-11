package dev.bl4cktrum.apts.api.models.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class MatchRequest {
    @NotBlank()
    private String patientDeviceId;
}
