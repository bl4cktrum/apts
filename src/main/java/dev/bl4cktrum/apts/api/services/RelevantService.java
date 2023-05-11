package dev.bl4cktrum.apts.api.services;

import dev.bl4cktrum.apts.api.models.entities.Patient;
import dev.bl4cktrum.apts.api.models.entities.PatientRelevant;
import dev.bl4cktrum.apts.api.models.entities.Relevant;
import dev.bl4cktrum.apts.api.models.requests.MatchRequest;
import dev.bl4cktrum.apts.api.repositories.PatientRelevantRepository;
import dev.bl4cktrum.apts.api.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RelevantService {
    private final PatientRelevantRepository patientRelevantRepository;
    private final PatientRepository patientRepository;

    @Transactional()
    public void match(MatchRequest request) {
        var patient = Patient.builder()
                .name(request.getName())
                .deviceId(UUID.fromString(request.getPatientDeviceId()))
                .build();
        patientRepository.save(patient);

        Relevant loggedUser = (Relevant) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var patientRelevant = PatientRelevant.builder()
                .relevant(loggedUser)
                .patient(patient)
                .build();
        patientRelevantRepository.save(patientRelevant);
    }
}
