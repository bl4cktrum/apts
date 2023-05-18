package dev.bl4cktrum.apts.api.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.bl4cktrum.apts.infrastructure.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "patient_relevant")
public class PatientRelevant extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "relevant_id")
    @JsonIgnore
    private Relevant relevant;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    @JsonIgnore
    private Patient patient;

    @OneToMany(mappedBy = "patientRelevant", cascade = CascadeType.ALL)
    private Set<Restriction> restrictions = new LinkedHashSet<>();
}