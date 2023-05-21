package dev.bl4cktrum.apts.api.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.bl4cktrum.apts.infrastructure.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "passivity_restrictions")
@DiscriminatorValue("passivity")
public class PassivityRestriction extends BaseEntity{
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restriction_id")
    @JsonIgnore
    Restriction restriction;

    @Column
    Duration duration;
}