package dev.bl4cktrum.apts.api.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.bl4cktrum.apts.infrastructure.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "restrictions")
public class Restriction extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_relevant_id", nullable = false)
    @JsonIgnore
    private PatientRelevant patientRelevant;

    @Column
    private String name;

    @Column
    private boolean is_active = true;

    @Cascade(CascadeType.ALL)
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "restriction")
    private Preference preference;

    @Cascade(CascadeType.ALL)
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "restriction")
    private PolygonRestriction polygonRestriction;

    @Cascade(CascadeType.ALL)
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "restriction")
    private CircleRestriction circleRestriction;

    @Cascade(CascadeType.ALL)
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "restriction")
    private PassivityRestriction passivityRestriction;
}