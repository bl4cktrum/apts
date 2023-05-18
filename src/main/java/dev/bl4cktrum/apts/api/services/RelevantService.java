package dev.bl4cktrum.apts.api.services;

import dev.bl4cktrum.apts.api.exceptions.ApiException;
import dev.bl4cktrum.apts.api.models.responses.PatientMatchResponse;
import dev.bl4cktrum.apts.api.models.entities.Patient;
import dev.bl4cktrum.apts.api.models.entities.PatientRelevant;
import dev.bl4cktrum.apts.api.models.entities.Relevant;
import dev.bl4cktrum.apts.api.models.requests.MatchRequest;
import dev.bl4cktrum.apts.api.repositories.PatientRelevantRepository;
import dev.bl4cktrum.apts.api.repositories.PatientRepository;
import dev.bl4cktrum.apts.api.repositories.RelevantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RelevantService {
    private final PatientRelevantRepository patientRelevantRepository;
    private final PatientRepository patientRepository;
    private final RelevantRepository relevantRepository;

    @Transactional()
    public PatientMatchResponse match(MatchRequest request) {
        Relevant loggedUser = (Relevant) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Patient patient = patientRepository.findByDeviceId(UUID.fromString(request.getPatient_device_id()))
                .orElse(Patient.builder()
                .name(request.getName())
                .patientRelevants(new HashSet<>())
                .deviceId(UUID.fromString(request.getPatient_device_id()))
                .build());

        if(patientRelevantRepository.findByPatient_IdAndRelevant_Id(patient.id, loggedUser.id).isPresent()) {
            throw new ApiException("This match is already defined.");
        }

        PatientRelevant patientRelevant = PatientRelevant.builder()
                .relevant(loggedUser)
                .patient(patient)
                .build();

//        patient.addPatientRelevant(patientRelevant); // ikisi de aynı set'e ekleme yaptığından double'lıyor
        loggedUser.addPatientRelevant(patientRelevant);

        patientRepository.save(patient);
        relevantRepository.save(loggedUser);
        return PatientMatchResponse.convertFromPatient(patient);
    }
}
