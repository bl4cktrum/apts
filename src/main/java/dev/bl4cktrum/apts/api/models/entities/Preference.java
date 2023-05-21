package dev.bl4cktrum.apts.api.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restriction_id",nullable = false)
    @JsonIgnore
    Restriction restriction;

    @Column(name = "sms_notification")
    private boolean sendSmsNotifications = true;
    @Column(name = "push_notification")
    private boolean sendPushNotification = true;
}