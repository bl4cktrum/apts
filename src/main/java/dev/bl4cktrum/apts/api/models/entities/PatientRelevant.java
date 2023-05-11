package dev.bl4cktrum.apts.api.models.entities;

import dev.bl4cktrum.apts.infrastructure.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "patient_relevant")
public class PatientRelevant extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "relevant_id")
    private Relevant relevant;

    @ManyToOne
    @JoinColumn(name = "patient_id",referencedColumnName = "id")
    private Patient patient;

    @OneToMany(mappedBy = "patientRelevant")
    private Set<Restriction> restrictions = new LinkedHashSet<>();
}