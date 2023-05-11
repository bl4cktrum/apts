package dev.bl4cktrum.apts.api.services;

import dev.bl4cktrum.apts.api.models.entities.PatientRelevant;
import dev.bl4cktrum.apts.api.models.entities.Relevant;
import dev.bl4cktrum.apts.api.models.requests.MatchRequest;
import dev.bl4cktrum.apts.api.repositories.PatientRelevantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RelevantService {
    private final PatientService patientService;
    private final PatientRelevantRepository patientRelevantRepository;

    public void match(MatchRequest request) {
        var patient = patientService.findByDeviceId(request.getPatientDeviceId());
        Relevant loggedUser = (Relevant) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var patientRelevant = PatientRelevant.builder()
                .relevant(loggedUser)
                .patient(patient)
                .build();
        patientRelevantRepository.save(patientRelevant);
    }
}
