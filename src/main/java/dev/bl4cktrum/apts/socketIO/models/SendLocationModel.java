package dev.bl4cktrum.apts.socketIO.models;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class SendLocationModel {
    private double latitude;
    private double longitude;
}
