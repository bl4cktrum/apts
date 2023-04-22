package dev.bl4cktrum.apts.api.repositories;

import dev.bl4cktrum.apts.api.models.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, String> {
}