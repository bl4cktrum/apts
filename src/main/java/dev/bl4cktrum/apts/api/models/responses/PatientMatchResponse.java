package dev.bl4cktrum.apts.api.models.responses;
import dev.bl4cktrum.apts.api.models.entities.Patient;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PatientMatchResponse {
    private String id;
    private String name;
    private String device_id;

    public static PatientMatchResponse convertFromPatient(Patient patient){
        return PatientMatchResponse.builder()
                .id(patient.getId())
                .name(patient.getName())
                .device_id(patient.getDeviceId().toString())
                .build();
    }
}
