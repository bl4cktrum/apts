package dev.bl4cktrum.apts.api.repositories;

import dev.bl4cktrum.apts.api.models.entities.Relevant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RelevantRepository extends JpaRepository<Relevant, String> {
    Optional<Relevant> findByEmail(String email);
}