package dev.bl4cktrum.apts.api.models.responses;

import dev.bl4cktrum.apts.api.models.dtos.PatientRelevantDto;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class GetPatientRelevantsResponse {
    private Set<PatientRelevantDto> patient_relevants;
}
