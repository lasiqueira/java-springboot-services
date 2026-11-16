package br.unisinos.apps4business.biometrics.message.dto;

import java.time.LocalDateTime;

/** Message sent through RabbitMQ, serialised as JSON (see RabbitConfiguration). */
public record BiometricsMessageDTO(String user, String data, LocalDateTime timestamp) {}
