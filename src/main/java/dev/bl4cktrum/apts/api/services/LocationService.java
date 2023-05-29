package dev.bl4cktrum.apts.api.services;

import com.corundumstudio.socketio.SocketIOServer;
import dev.bl4cktrum.apts.api.models.entities.*;
import dev.bl4cktrum.apts.api.repositories.LocationRepository;
import dev.bl4cktrum.apts.socketIO.models.NotificationDto;
import dev.bl4cktrum.apts.socketIO.models.SendLocationModel;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class LocationService {
    private final PatientService patientService;
    private final LocationRepository locationRepository;
    private final SocketIOServer socketIOServer;


    public void processNewLocation(String deviceId, Double latitude, Double longitude) {
        List<Relevant> relevants = patientService.getRelevantsByPatientDeviceId(deviceId);
        Patient patient = patientService.getPatientByDeviceId(deviceId);
        sendLocation(relevants, latitude, longitude);
        controlLocation(patient, latitude, longitude);
        insertLocation(patient, latitude, longitude);
    }

    public void sendLocation(List<Relevant> relevants, Double latitude, Double longitude) {
        relevants.forEach(relevant -> {
            try {
                socketIOServer.getClient(relevant.getSessionId())
                        .sendEvent("current_location",
                                SendLocationModel.builder()
                                        .latitude(latitude)
                                        .longitude(longitude)
                                        .build());
            } catch (Exception e) {
            }
        });
    }

    public void controlLocation(Patient patient, Double latitude, Double longitude) {
        Coordinate coordinate = new Coordinate(latitude, longitude);
        GeometryFactory geometryFactory = new GeometryFactory();
        Point newLocation = geometryFactory.createPoint(coordinate);

        Hibernate.initialize(patient.getPatientRelevants());
        patient.getPatientRelevants().forEach(match -> {
            Hibernate.initialize(match.getRestrictions());
            match.getRestrictions().forEach(restriction -> {
                if (!restriction.is_active())
                    return;
                CircleRestriction circleRestriction = restriction.getCircleRestriction();
                PolygonRestriction polygonRestriction = restriction.getPolygonRestriction();
                PassivityRestriction passivityRestriction = restriction.getPassivityRestriction();
                if (circleRestriction != null) {
                    // if he is outside
                    if (newLocation.getCoordinate().distance(circleRestriction.getCenter().getCoordinate()) > circleRestriction.getRadius()) {
                        Location lastLocation = patient.getLocations().stream().filter(Location::isLast).findFirst().orElseThrow();
                        // and recently not
                        if (lastLocation.getLocation().getCoordinate().distance(circleRestriction.getCenter().getCoordinate()) < circleRestriction.getRadius()) {
                            sendNotification(match, restriction, latitude, longitude);
                        }
                    }
                }
                if (polygonRestriction != null) {
                    if (!newLocation.intersects(polygonRestriction.getPolygon())) {
                        Location lastLocation = patient.getLocations().stream().filter(Location::isLast).findFirst().orElseThrow();
                        if (lastLocation.getLocation().intersects(polygonRestriction.getPolygon())) {
                            sendNotification(match, restriction, latitude, longitude);
                        }
                    }
                }
                if (passivityRestriction != null) {
                    sendNotification(match, restriction, latitude, longitude);
                }
            });
        });
    }

    private void sendNotification(PatientRelevant match, Restriction restriction, Double latitude, Double longitude) {
        socketIOServer.getClient(match.getRelevant().getSessionId()).sendEvent(
                "notification",
                NotificationDto.builder()
                        .title(restriction.getName())
                        .message("The patient is out of the field!")
                        .date(new Date().toString())
                        .patient_name(match.getName())
                        .latitude(latitude)
                        .longitude(longitude)
                        .build()
        );
    }

    public void insertLocation(Patient patient, Double latitude, Double longitude) {
        Coordinate coordinate = new Coordinate(latitude, longitude);
        GeometryFactory geometryFactory = new GeometryFactory();
        Point point = geometryFactory.createPoint(coordinate);

        Location newLocation = Location.builder()
                .patient(patient)
                .location(point)
                .date(new Date())
                .isLast(true)
                .build();

        Location lastLocation = patient.getLocations().stream()
                .filter(location ->
                        location.isLast() &&
                                !location.id.equals(newLocation.id)).findFirst().orElseThrow();
        lastLocation.setLast(false);

        locationRepository.save(lastLocation);
        locationRepository.save(newLocation);
    }
}
