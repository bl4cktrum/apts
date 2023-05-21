package dev.bl4cktrum.apts.api.adapters;

import dev.bl4cktrum.apts.api.models.dtos.PassivityRestrictionDto;
import dev.bl4cktrum.apts.api.models.entities.PassivityRestriction;
import org.springframework.stereotype.Component;

@Component
public class PassivityRestrictionDtoAdapter {
    public PassivityRestrictionDto fromPassivityRestriction(PassivityRestriction passivityRestriction){
        if (passivityRestriction==null)
            return null;

        return PassivityRestrictionDto.builder()
                .duration(passivityRestriction.getDuration())
                .build();
    }
}
