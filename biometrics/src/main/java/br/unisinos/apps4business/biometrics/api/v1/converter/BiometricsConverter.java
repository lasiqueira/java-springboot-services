package br.unisinos.apps4business.biometrics.api.v1.converter;

import br.unisinos.apps4business.biometrics.api.v1.dto.BiometricsRequestDTO;
import br.unisinos.apps4business.biometrics.entity.Biometrics;
import br.unisinos.apps4business.biometrics.message.dto.BiometricsMessageDTO;
import org.springframework.stereotype.Component;

@Component
public class BiometricsConverter {
    public Biometrics convertRequestDTOToEntity(BiometricsRequestDTO biometricsRequestDTO){
        return Biometrics
                .builder()
                .data(biometricsRequestDTO.data())
                .timestamp(biometricsRequestDTO.timestamp())
                .user(biometricsRequestDTO.user())
                .build();
    }
    public Biometrics convertMessageDTOToEntity(BiometricsMessageDTO biometricsMessageDTO){
        return Biometrics
                .builder()
                .data(biometricsMessageDTO.data())
                .timestamp(biometricsMessageDTO.timestamp())
                .user(biometricsMessageDTO.user())
                .build();
    }

    public BiometricsMessageDTO convertRequestDTOToMessageDTO(BiometricsRequestDTO biometricsRequestDTO){
        return new BiometricsMessageDTO(
                biometricsRequestDTO.user(),
                biometricsRequestDTO.data(),
                biometricsRequestDTO.timestamp());
    }
}
