package dev.bl4cktrum.apts.api.models.entities;

import dev.bl4cktrum.apts.infrastructure.abstracts.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Relevant extends BaseEntity {
    @Column(unique = true)
    private String email;
    private String name;

    @ManyToMany(mappedBy = "relevants")
    private Set<Patient> patients;
}
