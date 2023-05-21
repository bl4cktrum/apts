package dev.bl4cktrum.apts.api.models.responses;
import dev.bl4cktrum.apts.api.models.entities.Patient;
import dev.bl4cktrum.apts.api.models.entities.PatientRelevant;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PatientMatchResponse {
    private String patient_relevant_id;
    private String name;
    private boolean is_male;
    private String device_id;

    public static PatientMatchResponse convertFromPatient(Patient patient, PatientRelevant patientRelevant){
        return PatientMatchResponse.builder()
                .patient_relevant_id(patientRelevant.getId())
                .name(patientRelevant.getName())
                .is_male(patientRelevant.isMale())
                .device_id(patient.getDeviceId().toString())
                .build();
    }
}
