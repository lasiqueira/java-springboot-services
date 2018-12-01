package br.unisinos.apps4business.biometrics.entity;

import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Builder
@Entity
public class Biometrics {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String user;
    private String data;
    private LocalDateTime timestamp;
}
