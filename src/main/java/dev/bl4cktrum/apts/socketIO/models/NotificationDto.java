package dev.bl4cktrum.apts.socketIO.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationDto {
    private String title;
    private String message;
    private String date;
    private String patient_name;
    private Double latitude;
    private Double longitude;
}
