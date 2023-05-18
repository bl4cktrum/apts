package dev.bl4cktrum.apts.api.models.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse {
    private String message;
    private Object data;
}
