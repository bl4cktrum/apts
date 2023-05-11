package dev.bl4cktrum.apts.api.services;

import dev.bl4cktrum.apts.api.exceptions.ApiException;
import dev.bl4cktrum.apts.api.models.entities.Patient;
import dev.bl4cktrum.apts.api.models.requests.PatientCreateRequest;
import dev.bl4cktrum.apts.api.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    public Patient createPatient(PatientCreateRequest request){
        var patient = Patient.builder()
                .deviceId(UUID.fromString(request.getPatientDeviceId()))
                .name(request.getName())
                .build();
        patientRepository.save(patient);
        return patient;
    }

    public Patient findById(String patientId) {
        return patientRepository.findById(patientId).orElseThrow(() -> {
            throw new ApiException("Patient not found");
        });
    }

    public Patient findByDeviceId(String patientId) {
        return patientRepository.findByDeviceId(UUID.fromString(patientId)).orElseThrow(() -> {
            throw new ApiException("Patient device not found");
        });
    }
}
