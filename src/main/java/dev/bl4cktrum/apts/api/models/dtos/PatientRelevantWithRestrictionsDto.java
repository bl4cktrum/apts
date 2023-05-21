package dev.bl4cktrum.apts.api.models.dtos;

import dev.bl4cktrum.apts.api.models.entities.Restriction;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class PatientRelevantWithRestrictionsDto {
    private String id;
    private String name;
    private Set<RestrictionDto> restrictions;
}
