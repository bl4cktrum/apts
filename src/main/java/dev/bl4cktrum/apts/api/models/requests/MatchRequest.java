package dev.bl4cktrum.apts.api.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.Date;

@Data
public class MatchRequest {
    @NotBlank
    @Pattern(regexp="^[0-9A-Fa-f]{8}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{12}$")
    private String patient_device_id;

    @NotBlank
    private String name;

    @NotNull
    private Date birth_date;

    private boolean is_male = true;
}
