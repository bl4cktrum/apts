package dev.bl4cktrum.apts.api.models.requests;

import lombok.Data;

@Data
public class PreferenceUpdateRequest {
    private boolean sendSmsNotifications = true;
    private boolean sendPushNotification = true;
}
