package dev.bl4cktrum.apts.api.services;

import dev.bl4cktrum.apts.api.exceptions.ApiException;
import dev.bl4cktrum.apts.api.models.entities.*;
import dev.bl4cktrum.apts.api.models.requests.CircleRestrictionCreateRequest;
import dev.bl4cktrum.apts.api.models.requests.PolygonRestrictionCreateRequest;
import dev.bl4cktrum.apts.api.models.requests.PreferenceUpdateRequest;
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
    private final PreferenceRepository preferenceRepository;

    @Transactional
    public void polygonRestrictionCreate(PolygonRestrictionCreateRequest request) {
        Relevant loggedUser = (Relevant) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PatientRelevant patientRelevant = loggedUser.getPatientRelevants().stream()
                .filter(pr -> pr.id.equals(request.getPatient_relevant_id())).findFirst().orElseThrow(
                () -> {throw new ApiException("Match Not Found");}
        );

        GeometryFactory geometryFactory = new GeometryFactory();
        Coordinate[] coordinates = new Coordinate[request.getPoints().size()];
        for (int i = 0; i < request.getPoints().size(); i++) {
            coordinates[i]=new Coordinate(
                    request.getPoints().get(i).get(0), request.getPoints().get(i).get(1));
        }
        Polygon polygon = geometryFactory.createPolygon(coordinates);
        PolygonRestriction polygonRestriction = PolygonRestriction.builder()
                .polygon(polygon)
                .build();

        Preference preference = Preference.builder()
                .sendPushNotification(true)
                .sendSmsNotifications(true)
                .build();

        Restriction restriction = Restriction.builder()
                .name(request.getName())
                .is_active(request.is_active())
                .patientRelevant(patientRelevant)
                .preference(preference)
                .polygonRestriction(polygonRestriction)
                .build();

        preference.setRestriction(restriction);
        polygonRestriction.setRestriction(restriction);
        restrictionRepository.save(restriction);
    }

    @Transactional
    public void circleRestrictionCreate(CircleRestrictionCreateRequest request) {
        Relevant loggedUser = (Relevant) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PatientRelevant patientRelevant = loggedUser.getPatientRelevants().stream()
                .filter(pr -> pr.id.equals(request.getPatient_relevant_id())).findFirst().orElseThrow(
                        () -> {throw new ApiException("Match Not Found");}
                );


        GeometryFactory geometryFactory = new GeometryFactory();
        Point point = geometryFactory.createPoint(new Coordinate(request.getCenter().get(0),request.getCenter().get(1)));
        CircleRestriction circleRestriction = CircleRestriction.builder()
                .center(point)
                .radius(request.getRadius())
                .build();

        Preference preference = Preference.builder()
                .sendPushNotification(true)
                .sendSmsNotifications(true)
                .build();

        Restriction restriction = Restriction.builder()
                .name(request.getName())
                .preference(preference)
                .circleRestriction(circleRestriction)
                .is_active(request.is_active())
                .patientRelevant(patientRelevant)
                .build();

        preference.setRestriction(restriction);
        circleRestriction.setRestriction(restriction);
        restrictionRepository.save(restriction);
    }

    public void activationUpdate(String restrictionId, boolean isActive) {
        Restriction restriction = getRestriction(restrictionId);
        restriction.set_active(isActive);
        restrictionRepository.save(restriction);
    }

    public void preferenceUpdate(String restrictionId, PreferenceUpdateRequest request) {
        Restriction restriction = getRestriction(restrictionId);
        Preference preference = restriction.getPreference();
        preference.setSendPushNotification(request.isSendPushNotification());
        preference.setSendSmsNotifications(request.isSendSmsNotifications());
        preferenceRepository.save(preference);
    }

    private Restriction getRestriction(String restrictionId) {
        Restriction restriction = restrictionRepository.findById(restrictionId).orElseThrow(
                ()-> {throw new ApiException("Restriction not found");}
        );
        if(!restriction.getPatientRelevant().getRelevant().id.equals(
                ((Relevant)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId()
        ))
            throw new ApiException("Not granted to modify this restriction");
        return restriction;
    }
}
