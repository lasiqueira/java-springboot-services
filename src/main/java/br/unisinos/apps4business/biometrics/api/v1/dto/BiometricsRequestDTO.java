package br.unisinos.apps4business.biometrics.api.v1.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record BiometricsRequestDTO (
    @NotNull
    String user,
    @NotNull
    String data,
    @NotNull
    LocalDateTime timestamp
){}


