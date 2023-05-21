package dev.bl4cktrum.apts.api.models.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PolygonRestrictionDto {
    private List<List<Double>> corners;
}
