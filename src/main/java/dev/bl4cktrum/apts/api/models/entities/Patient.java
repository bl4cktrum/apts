package dev.bl4cktrum.apts.api.models.entities;

import dev.bl4cktrum.apts.infrastructure.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "patient")
@NoArgsConstructor
public class Patient extends BaseEntity {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
    private Set<Location> locations;

    @ManyToMany
    @JoinTable(name = "patient_relevant", joinColumns = @JoinColumn(name = "patient_id"))
    private Set<Relevant> relevants = new LinkedHashSet<>();

}