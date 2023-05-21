package dev.bl4cktrum.apts.api.adapters;

import dev.bl4cktrum.apts.api.models.dtos.PatientRelevantWithRestrictionsDto;
import dev.bl4cktrum.apts.api.models.dtos.RestrictionDto;
import dev.bl4cktrum.apts.api.models.entities.PatientRelevant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class PatientRelevantWithRestrictionsAdapter {
    private final RestrictionDtoAdapter restrictionDtoAdapter;
    public PatientRelevantWithRestrictionsDto fromPatientRelevant(PatientRelevant patientRelevant){
        Set<RestrictionDto> restrictionDtoSet = new LinkedHashSet<>();
        patientRelevant.getRestrictions().forEach(restriction -> restrictionDtoSet.add(restrictionDtoAdapter.fromRestriction(restriction)));
        return PatientRelevantWithRestrictionsDto.builder()
                .id(patientRelevant.id)
                .name(patientRelevant.getName())
                .restrictions(restrictionDtoSet)
                .build();
    }
}
