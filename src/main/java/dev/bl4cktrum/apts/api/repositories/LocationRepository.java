package dev.bl4cktrum.apts.api.repositories;

import dev.bl4cktrum.apts.api.models.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, String> {
}