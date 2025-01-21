package com.fedelizondo.challenge.infrastructure.adapter.in.rest.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponseMessage(LocalDateTime timestamp, int status, String error, String message,
                                   List<String> errors) {
}

