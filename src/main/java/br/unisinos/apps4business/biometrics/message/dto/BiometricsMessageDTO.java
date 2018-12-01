package br.unisinos.apps4business.biometrics.message.dto;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@Getter
public class BiometricsMessageDTO  implements Serializable {
    private static final long serialVersionID = 4L;
    private String user;
    private String data;
    private LocalDateTime timestamp;
}
