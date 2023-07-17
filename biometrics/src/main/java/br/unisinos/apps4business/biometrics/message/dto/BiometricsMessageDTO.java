package br.unisinos.apps4business.biometrics.message.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class BiometricsMessageDTO  implements Serializable {
    private static final long serialVersionID = 4L;
    private String user;
    private String data;
    private LocalDateTime timestamp;

    BiometricsMessageDTO(String user, String data, LocalDateTime timestamp) {
        this.user = user;
        this.data = data;
        this.timestamp = timestamp;
    }

    public static BiometricsMessageDTOBuilder builder() {
        return new BiometricsMessageDTOBuilder();
    }

    public String getUser() {
        return this.user;
    }

    public String getData() {
        return this.data;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

    public static class BiometricsMessageDTOBuilder {
        private String user;
        private String data;
        private LocalDateTime timestamp;

        BiometricsMessageDTOBuilder() {
        }

        public BiometricsMessageDTO.BiometricsMessageDTOBuilder user(String user) {
            this.user = user;
            return this;
        }

        public BiometricsMessageDTO.BiometricsMessageDTOBuilder data(String data) {
            this.data = data;
            return this;
        }

        public BiometricsMessageDTO.BiometricsMessageDTOBuilder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public BiometricsMessageDTO build() {
            return new BiometricsMessageDTO(user, data, timestamp);
        }

        public String toString() {
            return "BiometricsMessageDTO.BiometricsMessageDTOBuilder(user=" + this.user + ", data=" + this.data + ", timestamp=" + this.timestamp + ")";
        }
    }
}
