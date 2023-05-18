package dev.bl4cktrum.apts.api.repositories;

import dev.bl4cktrum.apts.api.models.entities.PatientRelevant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRelevantRepository extends JpaRepository<PatientRelevant, String> {
    Optional<PatientRelevant> findByPatient_IdAndRelevant_Id(String patientID, String relevantID);

}