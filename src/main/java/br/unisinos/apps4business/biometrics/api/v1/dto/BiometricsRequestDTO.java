package br.unisinos.apps4business.biometrics.api.v1.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class BiometricsRequestDTO {
    @NotNull
    private String user;
    @NotNull
    private String data;
    @NotNull
    private LocalDateTime timestamp;


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
