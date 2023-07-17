package br.unisinos.apps4business.biometrics.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Biometrics {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String user;
    private String data;
    private LocalDateTime timestamp;

    Biometrics(Long id, String user, String data, LocalDateTime timestamp) {
        this.id = id;
        this.user = user;
        this.data = data;
        this.timestamp = timestamp;
    }

    public static BiometricsBuilder builder() {
        return new BiometricsBuilder();
    }

    public static class BiometricsBuilder {
        private Long id;
        private String user;
        private String data;
        private LocalDateTime timestamp;

        BiometricsBuilder() {
        }

        public BiometricsBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public BiometricsBuilder user(String user) {
            this.user = user;
            return this;
        }

        public BiometricsBuilder data(String data) {
            this.data = data;
            return this;
        }

        public BiometricsBuilder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Biometrics build() {
            return new Biometrics(id, user, data, timestamp);
        }

        public String toString() {
            return "Biometrics.BiometricsBuilder(id=" + this.id + ", user=" + this.user + ", data=" + this.data + ", timestamp=" + this.timestamp + ")";
        }
    }
}
