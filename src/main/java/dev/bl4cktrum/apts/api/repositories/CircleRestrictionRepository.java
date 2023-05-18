package dev.bl4cktrum.apts.api.repositories;

import dev.bl4cktrum.apts.api.models.entities.CircleRestriction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CircleRestrictionRepository extends JpaRepository<CircleRestriction, String> {
}