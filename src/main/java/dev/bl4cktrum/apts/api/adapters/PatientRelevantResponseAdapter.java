package dev.bl4cktrum.apts.api.adapters;

import dev.bl4cktrum.apts.api.models.entities.PatientRelevant;
import dev.bl4cktrum.apts.api.models.dtos.PatientRelevantDto;
import org.springframework.stereotype.Component;

@Component
public class PatientRelevantResponseAdapter {
    public PatientRelevantDto fromPatientRelevant(PatientRelevant pr){
        return PatientRelevantDto.builder()
                .is_male(pr.isMale())
                .birth_date(pr.getBirthDate())
                .name(pr.getName())
                .id(pr.id)
                .build();
    }
}
