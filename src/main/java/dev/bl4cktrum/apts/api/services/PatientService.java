package dev.bl4cktrum.apts.api.services;

import dev.bl4cktrum.apts.api.exceptions.ApiException;
import dev.bl4cktrum.apts.api.models.entities.Location;
import dev.bl4cktrum.apts.api.models.entities.Patient;
import dev.bl4cktrum.apts.api.models.requests.LocationRequest;
import dev.bl4cktrum.apts.api.repositories.LocationRepository;
import dev.bl4cktrum.apts.api.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final LocationRepository locationRepository;

    @Transactional
    public void newLocation(LocationRequest request) {
        Patient patient = patientRepository.findByDeviceId(UUID.fromString(request.getPatient_device_id())).orElseThrow(() -> {
            throw new ApiException("Patient Not Found");
        });
        Coordinate coordinate = new Coordinate(
                request.getLocation().get(0),
                request.getLocation().get(1));

        GeometryFactory geometryFactory = new GeometryFactory();
        Point point = geometryFactory.createPoint(coordinate);

        Location location = Location.builder()
                .patient(patient)
                .location(point)
                .date(new Date())
                .build();
        locationRepository.save(location);
    }
}
