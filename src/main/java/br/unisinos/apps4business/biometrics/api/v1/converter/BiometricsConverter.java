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
                .data(biometricsRequestDTO.getData())
                .timestamp(biometricsRequestDTO.getTimestamp())
                .user(biometricsRequestDTO.getUser())
                .build();
    }
    public Biometrics convertMessageDTOToEntity(BiometricsMessageDTO biometricsMessageDTO){
        return Biometrics
                .builder()
                .data(biometricsMessageDTO.getData())
                .timestamp(biometricsMessageDTO.getTimestamp())
                .user(biometricsMessageDTO.getUser())
                .build();
    }

    public BiometricsMessageDTO convertRequestDTOToMessageDTO(BiometricsRequestDTO biometricsRequestDTO){
        return BiometricsMessageDTO
                .builder()
                .data(biometricsRequestDTO.getData())
                .timestamp(biometricsRequestDTO.getTimestamp())
                .user(biometricsRequestDTO.getUser())
                .build();
    }
}
