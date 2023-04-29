package dev.bl4cktrum.apts.api.models.entities;

import dev.bl4cktrum.apts.infrastructure.abstracts.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity(name = "locations")
public class Location extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "patient_id",nullable = false)
    private Patient patient;
    private float lat;
    private float lng;
}