package com.mxrpheus.productservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(description = "Data Transfer Object for exceptions")
public record ExceptionDto(

        @Schema(description = "HTTP status", example = "500")
        HttpStatus status,

        @Schema(description = "Error message with the issue", example = "Internal server error")
        String message,

        @Schema(description = "Timestamp when the error occurred", example = "2025-05-23T11:52:52.353Z")
        LocalDateTime timestamp

) {
}
