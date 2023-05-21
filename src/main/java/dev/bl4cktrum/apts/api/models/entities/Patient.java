package dev.bl4cktrum.apts.api.models.entities;

import dev.bl4cktrum.apts.infrastructure.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "patients")
public class Patient extends BaseEntity {
    @Column(name = "device_id",unique = true)
    private UUID deviceId;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private Set<Location> locations;

    @OneToMany(mappedBy = "patient")
    private Set<PatientRelevant> patientRelevants = new LinkedHashSet<>();

    public void addPatientRelevant(PatientRelevant pr){
        this.patientRelevants.add(pr);
        pr.setPatient(this);
    }
}