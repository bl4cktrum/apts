package dev.bl4cktrum.apts.api.models.entities;

import dev.bl4cktrum.apts.infrastructure.abstracts.BaseEntity;
import dev.bl4cktrum.apts.infrastructure.enums.RestrictionType;
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
    private boolean is_active;

    @ManyToOne
    private PatientRelevant patientRelevant;

    @Enumerated(EnumType.STRING)
    private RestrictionType restrictionType;

    @OneToOne
    @Cascade(CascadeType.PERSIST)
    @JoinColumn(name = "preference_id",nullable = false)
    private Preference preference;


    @Override
    public void onCreate(){
             super.onCreate();
             this.preference = Preference.builder()
                .restriction(this)
                .sendPushNotification(true)
                .senSmsNotifications(true)
                .build();
    }
    //TODO: TEST THIS
}