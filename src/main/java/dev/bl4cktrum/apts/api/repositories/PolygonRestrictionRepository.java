package dev.bl4cktrum.apts.api.repositories;

import dev.bl4cktrum.apts.api.models.entities.PolygonRestriction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolygonRestrictionRepository extends JpaRepository<PolygonRestriction, String> {
}