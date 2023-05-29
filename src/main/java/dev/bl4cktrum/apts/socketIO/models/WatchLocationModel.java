package dev.bl4cktrum.apts.socketIO.models;

import lombok.Data;

@Data
public class WatchLocationModel {
    private Double latitude;
    private Double longitude;
    private String device_id;
}
