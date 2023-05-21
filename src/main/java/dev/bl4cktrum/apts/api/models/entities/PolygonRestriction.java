package dev.bl4cktrum.apts.api.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.bl4cktrum.apts.infrastructure.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Polygon;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "polygon_restrictions")
public class PolygonRestriction extends BaseEntity{
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restriction_id",nullable = false)
    @JsonIgnore
    Restriction restriction;

    @Column(name = "polygon", columnDefinition = "geometry(Polygon,4326)")
    private Polygon polygon;
}