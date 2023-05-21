package dev.bl4cktrum.apts.api.models.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class PatientRelevantDto {
    private String id;
    private String name;
    private Date birth_date;
    private boolean is_male;
}
