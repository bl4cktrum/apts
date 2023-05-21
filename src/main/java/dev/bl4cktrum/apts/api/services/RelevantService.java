package dev.bl4cktrum.apts.api.services;

import dev.bl4cktrum.apts.api.adapters.PatientRelevantResponseAdapter;
import dev.bl4cktrum.apts.api.adapters.PatientRelevantWithRestrictionsAdapter;
import dev.bl4cktrum.apts.api.exceptions.ApiException;
import dev.bl4cktrum.apts.api.models.dtos.PatientRelevantWithRestrictionsDto;
import dev.bl4cktrum.apts.api.models.responses.PatientMatchResponse;
import dev.bl4cktrum.apts.api.models.entities.Patient;
import dev.bl4cktrum.apts.api.models.entities.PatientRelevant;
import dev.bl4cktrum.apts.api.models.entities.Relevant;
import dev.bl4cktrum.apts.api.models.requests.MatchRequest;
import dev.bl4cktrum.apts.api.models.dtos.PatientRelevantDto;
import dev.bl4cktrum.apts.api.repositories.PatientRelevantRepository;
import dev.bl4cktrum.apts.api.repositories.PatientRepository;
import dev.bl4cktrum.apts.api.repositories.RelevantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RelevantService {
    private final PatientRelevantRepository patientRelevantRepository;
    private final PatientRepository patientRepository;
    private final RelevantRepository relevantRepository;
    private final PatientRelevantResponseAdapter patientRelevantResponseAdapter;
    private final PatientRelevantWithRestrictionsAdapter patientRelevantWithRestrictionsAdapter;

    @Transactional()
    public PatientMatchResponse match(MatchRequest request) {
        Relevant loggedUser = (Relevant) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Patient patient = patientRepository.findByDeviceId(UUID.fromString(request.getPatient_device_id()))
                .orElse(Patient.builder()
                .patientRelevants(new HashSet<>())
                .deviceId(UUID.fromString(request.getPatient_device_id()))
                .build());

        if(patientRelevantRepository.findByPatient_IdAndRelevant_Id(patient.id, loggedUser.id).isPresent()) {
            throw new ApiException("This match is already defined.");
        }

        PatientRelevant patientRelevant = PatientRelevant.builder()
                .relevant(loggedUser)
                .patient(patient)
                .name(request.getName())
                .birthDate(request.getBirth_date())
                .isMale(request.is_male())
                .build();

        patient.addPatientRelevant(patientRelevant);
        loggedUser.addPatientRelevant(patientRelevant);

        patientRepository.save(patient);
        patientRelevantRepository.save(patientRelevant);
        relevantRepository.save(loggedUser);
        return PatientMatchResponse.convertFromPatient(patient, patientRelevant);
    }

    public Set<PatientRelevantDto> getDevices() {
        Relevant loggedUser = (Relevant) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<PatientRelevant> data = loggedUser.getPatientRelevants();
        Set<PatientRelevantDto> result = new LinkedHashSet<>();
        data.forEach(pr -> result.add(patientRelevantResponseAdapter.fromPatientRelevant(pr)));
        return result;
    }

    public PatientRelevantWithRestrictionsDto getPatientRelevantRestrictions(String id) {
        PatientRelevant patientRelevant =  patientRelevantRepository.findById(id).orElseThrow(() -> {
            throw new ApiException("Match not found");
        });
        return patientRelevantWithRestrictionsAdapter.fromPatientRelevant(patientRelevant);
    }
}
