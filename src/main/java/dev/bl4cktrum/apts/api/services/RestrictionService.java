package dev.bl4cktrum.apts.api.services;

import dev.bl4cktrum.apts.api.exceptions.ApiException;
import dev.bl4cktrum.apts.api.models.entities.PolygonRestriction;
import dev.bl4cktrum.apts.api.models.entities.Patient;
import dev.bl4cktrum.apts.api.models.entities.PatientRelevant;
import dev.bl4cktrum.apts.api.models.entities.Restriction;
import dev.bl4cktrum.apts.api.models.requests.RestrictionCreateRequest;
import dev.bl4cktrum.apts.api.repositories.PatientRelevantRepository;
import dev.bl4cktrum.apts.api.repositories.PatientRepository;
import dev.bl4cktrum.apts.api.repositories.RestrictionRepository;
import dev.bl4cktrum.apts.infrastructure.enums.RestrictionType;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class RestrictionService {
    private final RestrictionRepository restrictionRepository;
    private final PatientRepository patientRepository;
    private final PatientRelevantRepository patientRelevantRepository;

    public void restrictionCreate(RestrictionCreateRequest request) {
        Patient patient = patientRepository.findById(request.getPatient_id())
                .orElseThrow(() -> {
            throw new ApiException("Patient device not found");
        });
        PatientRelevant patientRelevant = patientRelevantRepository.findByPatient(patient)
                .orElseThrow(() -> {
                    throw new ApiException("No match between patient and user!");
                });
        Restriction restriction = Restriction.builder()
                .name(request.getName())
                .is_active(request.is_active())
                .patientRelevant(patientRelevant)
                .build();

        switch (RestrictionType.get(request.getRestrictionType())){
            case POLYGON -> {
                GeometryFactory geometryFactory = new GeometryFactory();
                ArrayList<Coordinate> coordinatesAL = new ArrayList<>();
                for (int i = 0; i < request.getPoints().size(); i++) {
                    coordinatesAL.add(new Coordinate(
                            request.getPoints().get(i).get(0), request.getPoints().get(i).get(1)));
                }

                Polygon polygon = geometryFactory.createPolygon((Coordinate[]) coordinatesAL.toArray());
                PolygonRestriction polygonRestriction = PolygonRestriction.builder()
                        .polygon(polygon)
                        .build();

                polygonRestriction.setRestriction(restriction);
            }
            case CIRCLE -> {
                System.out.println("CIRCLE");
            }
            case PASSIVITY -> {
                System.out.println("PASSIVITY");
            }
        }
        restrictionRepository.save(restriction);
    }
}
