package dev.bl4cktrum.apts.api.services;

import dev.bl4cktrum.apts.api.exceptions.ApiException;
import dev.bl4cktrum.apts.api.models.entities.Patient;
import dev.bl4cktrum.apts.api.models.entities.Relevant;
import dev.bl4cktrum.apts.api.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;

    @Transactional
    public List<Relevant> getRelevantsByPatientDeviceId(String deviceId) {
        Patient patient = patientRepository.findByDeviceId(UUID.fromString(deviceId)).orElseThrow(()->{throw new ApiException("Patient");});
        Hibernate.initialize(patient.getPatientRelevants());
        return patient.getPatientRelevants().stream().map(patientRelevant -> {
            Hibernate.initialize(patientRelevant.getRelevant());
            return patientRelevant.getRelevant();
        }).toList();
    }

    public Patient getPatientByDeviceId(String deviceId) {
        return patientRepository.findByDeviceId(UUID.fromString(deviceId)).orElseThrow(()->{throw new ApiException("Patient not found");});
    }
}
