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
@Entity(name = "preferences")
public class Preference extends BaseEntity {
    @Column(name = "sms_notification")
    private boolean senSmsNotifications = true;
    @Column(name = "push_notification")
    private boolean sendPushNotification = true;

    @OneToOne
    @JoinColumn(name = "restriction_id",nullable = false)
    private Restriction restriction;
}