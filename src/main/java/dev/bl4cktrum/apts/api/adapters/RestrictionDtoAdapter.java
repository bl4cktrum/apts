package dev.bl4cktrum.apts.api.adapters;

import dev.bl4cktrum.apts.api.models.dtos.RestrictionDto;
import dev.bl4cktrum.apts.api.models.entities.Restriction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RestrictionDtoAdapter {
    private final PolygonRestrictionDtoAdapter polygonAdapter;
    private final CircleRestrictionDtoAdapter circleAdapter;
    private final PassivityRestrictionDtoAdapter passivityAdapter;
    public RestrictionDto fromRestriction(Restriction restriction){
        return RestrictionDto.builder()
                .polygonRestriction(polygonAdapter.fromPolygonRestriction(restriction.getPolygonRestriction()))
                .circleRestriction(circleAdapter.fromCircleRestriction(restriction.getCircleRestriction()))
                .passivityRestriction(passivityAdapter.fromPassivityRestriction(restriction.getPassivityRestriction()))
                .preference(restriction.getPreference())
                .is_active(restriction.is_active())
                .name(restriction.getName())
                .id(restriction.id)
                .build();
    }
}
