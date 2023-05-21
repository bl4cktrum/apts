package dev.bl4cktrum.apts.api.adapters;

import dev.bl4cktrum.apts.api.exceptions.ApiException;
import dev.bl4cktrum.apts.api.models.dtos.CircleRestrictionDto;
import dev.bl4cktrum.apts.api.models.entities.CircleRestriction;
import org.springframework.stereotype.Component;


import java.util.Arrays;
import java.util.List;

@Component
public class CircleRestrictionDtoAdapter {
    public CircleRestrictionDto fromCircleRestriction(CircleRestriction circleRestriction){
        if (circleRestriction==null)
            return null;

        List<Double> center;
        try {
            Double x = circleRestriction.getCenter().getCoordinate().getX();
            Double y = circleRestriction.getCenter().getCoordinate().getY();
            center = Arrays.asList(x,y);
        }catch (Exception e){
            throw new ApiException(e.getMessage());
        }
        return CircleRestrictionDto.builder()
                .center(center)
                .radius(circleRestriction.getRadius())
                .build();
    }
}
