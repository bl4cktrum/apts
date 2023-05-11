package dev.bl4cktrum.apts.api.repositories;

import dev.bl4cktrum.apts.api.models.entities.PatientRelevant;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PatientRelevantRepository extends JpaRepository<PatientRelevant, String> {
}