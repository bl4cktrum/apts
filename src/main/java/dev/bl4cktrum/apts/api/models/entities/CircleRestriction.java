package dev.bl4cktrum.apts.api.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.bl4cktrum.apts.infrastructure.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "circle_restrictions")
public class CircleRestriction extends BaseEntity{
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restriction_id")
    @JsonIgnore
    Restriction restriction;

    @Column(columnDefinition = "geometry(Point,4326)")
    private Point center;

    @Column
    private double radius;
}