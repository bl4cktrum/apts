package dev.bl4cktrum.apts.api.adapters;

import dev.bl4cktrum.apts.api.exceptions.ApiException;
import dev.bl4cktrum.apts.api.models.dtos.PolygonRestrictionDto;
import dev.bl4cktrum.apts.api.models.entities.PolygonRestriction;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Polygon;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PolygonRestrictionDtoAdapter {
    public PolygonRestrictionDto fromPolygonRestriction(PolygonRestriction polygonRestriction){
        if (polygonRestriction==null)
                return null;
        List<List<Double>> points = new ArrayList<>();
        try {
            for (Coordinate coordinate : polygonRestriction.getPolygon().getCoordinates()) {
                double x  = coordinate.getX();
                double y  = coordinate.getY();
                points.add(Arrays.asList(x,y));
            }
        }catch (Exception e){
            throw new ApiException(e.getMessage());
        }
            return PolygonRestrictionDto.builder()
                    .corners(points)
                    .build();
    }
}
