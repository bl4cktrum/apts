package dev.bl4cktrum.apts.api.services;

import dev.bl4cktrum.apts.api.exceptions.ApiException;
import dev.bl4cktrum.apts.api.models.entities.*;
import dev.bl4cktrum.apts.api.models.requests.CircleRestrictionCreateRequest;
import dev.bl4cktrum.apts.api.models.requests.PolygonRestrictionCreateRequest;
import dev.bl4cktrum.apts.api.repositories.*;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class RestrictionService {
    private final RestrictionRepository restrictionRepository;
    private final PatientRepository patientRepository;
    private final PolygonRestrictionRepository polygonRestrictionRepository;
    private final CircleRestrictionRepository circleRestrictionRepository;
    private final PreferenceRepository preferenceRepository;

    @Transactional
    public void polygonRestrictionCreate(PolygonRestrictionCreateRequest request) {
        Patient patient = getPatientById(request.getPatient_id());
        Relevant loggedUser = (Relevant) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PatientRelevant patientRelevant = patient.getPatientRelevants().stream().filter(patientRelevant1 -> patientRelevant1.getRelevant().id.equals(loggedUser.id)).findFirst().orElseThrow(
                () -> {throw new ApiException("Match Not Found");}
        );

        Restriction restriction = Restriction.builder()
                .name(request.getName())
                .is_active(request.is_active())
                .patientRelevant(patientRelevant)
                .build();
        Preference preference = Preference.builder()
                .restriction(restriction)
                .sendPushNotification(true)
                .senSmsNotifications(true)
                .build();

        GeometryFactory geometryFactory = new GeometryFactory();
        Coordinate[] coordinates = new Coordinate[request.getPoints().size()];
        for (int i = 0; i < request.getPoints().size(); i++) {
            coordinates[i]=new Coordinate(
                    request.getPoints().get(i).get(0), request.getPoints().get(i).get(1));
        }

        Polygon polygon = geometryFactory.createPolygon(coordinates);
        PolygonRestriction polygonRestriction = PolygonRestriction.builder()
                .restriction(restriction)
                .polygon(polygon)
                .build();

        restrictionRepository.save(restriction);
        preferenceRepository.save(preference);
        polygonRestrictionRepository.save(polygonRestriction);
    }

    @Transactional
    public void circleRestrictionCreate(CircleRestrictionCreateRequest request) {
        Patient patient = getPatientById(request.getPatient_id());
        Relevant loggedUser = (Relevant) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PatientRelevant patientRelevant = patient.getPatientRelevants().stream().filter(patientRelevant1 -> patientRelevant1.getRelevant().id.equals(loggedUser.id)).findFirst().orElseThrow(
                () -> {throw new ApiException("Match Not Found");}
        );

        Restriction restriction = Restriction.builder()
                .name(request.getName())
                .is_active(request.is_active())
                .patientRelevant(patientRelevant)
                .build();
        Preference preference = Preference.builder()
                .restriction(restriction)
                .sendPushNotification(true)
                .senSmsNotifications(true)
                .build();

        GeometryFactory geometryFactory = new GeometryFactory();
        Point point = geometryFactory.createPoint(new Coordinate(request.getCenter().get(0),request.getCenter().get(1)));
        CircleRestriction circleRestriction = CircleRestriction.builder()
                .restriction(restriction)
                .center(point)
                .radius(request.getRadius())
                .build();

        restrictionRepository.save(restriction);
        preferenceRepository.save(preference);
        circleRestrictionRepository.save(circleRestriction);
    }

    private Patient getPatientById(String  patientId) {
        return patientRepository.findById(patientId)
                .orElseThrow(() -> {
                    throw new ApiException("Patient not found");
                });
    }
}
