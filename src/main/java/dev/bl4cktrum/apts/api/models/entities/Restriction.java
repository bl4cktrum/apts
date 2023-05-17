package dev.bl4cktrum.apts.api.models.entities;

import dev.bl4cktrum.apts.infrastructure.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "restrictions")
public class Restriction extends BaseEntity {
    @Column
    private String name;

    @Column
    private boolean is_active = true;

    @ManyToOne
    private PatientRelevant patientRelevant;


    @OneToOne
    @Cascade(CascadeType.PERSIST)
    @JoinColumn(name = "preference_id",nullable = false)
    private Preference preference = Preference.builder()
            .restriction(this)
            .sendPushNotification(true)
            .senSmsNotifications(true)
            .build();

    //TODO: TEST THIS
}