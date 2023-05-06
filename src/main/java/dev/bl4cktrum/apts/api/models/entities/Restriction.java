package dev.bl4cktrum.apts.api.models.entities;

import dev.bl4cktrum.apts.infrastructure.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "restrictions")
public class Restriction extends BaseEntity {
    @ManyToOne()
    private PatientRelevant patientRelevant;
}