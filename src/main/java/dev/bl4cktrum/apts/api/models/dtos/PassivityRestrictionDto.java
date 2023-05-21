package dev.bl4cktrum.apts.api.models.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.Duration;

@Data
@Builder
public class PassivityRestrictionDto {
    private Duration duration;
}
