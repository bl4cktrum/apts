package dev.bl4cktrum.apts.api.models.dtos;

import dev.bl4cktrum.apts.api.models.entities.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestrictionDto {
    private String id;

    private String name;

    private boolean is_active = true;

    private Preference preference;

    private PolygonRestrictionDto polygonRestriction;

    private CircleRestrictionDto circleRestriction;

    private PassivityRestrictionDto passivityRestriction;
}
