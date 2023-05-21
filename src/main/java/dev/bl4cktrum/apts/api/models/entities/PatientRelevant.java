package dev.bl4cktrum.apts.api.models.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import dev.bl4cktrum.apts.infrastructure.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "patient_relevant")
public class PatientRelevant extends BaseEntity {
    @Column
    private String name;

    @Column(name = "is_male")
    private boolean isMale;

    @Column(name = "birth_date")
    private Date birthDate;

    @ManyToOne
    @JoinColumn(name = "relevant_id")
    @JsonIgnore
    private Relevant relevant;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    @JsonIgnore
    private Patient patient;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "patientRelevant")
    @Cascade(CascadeType.ALL)
    private Set<Restriction> restrictions = new LinkedHashSet<>();
}