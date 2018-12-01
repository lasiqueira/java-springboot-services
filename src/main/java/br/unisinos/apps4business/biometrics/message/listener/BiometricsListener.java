package br.unisinos.apps4business.biometrics.message.listener;

import br.unisinos.apps4business.biometrics.api.v1.converter.BiometricsConverter;
import br.unisinos.apps4business.biometrics.message.dto.BiometricsMessageDTO;
import br.unisinos.apps4business.biometrics.service.BiometricsService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class BiometricsListener {
    private BiometricsService biometricsService;
    private BiometricsConverter biometricsConverter;

    public BiometricsListener(BiometricsService biometricsService, BiometricsConverter biometricsConverter) {
        this.biometricsService = biometricsService;
        this.biometricsConverter = biometricsConverter;
    }

    @RabbitListener(queues = "queue")
    public void handle(BiometricsMessageDTO biometricsMessageDTO){
        biometricsService.saveBiometrics(biometricsConverter.convertMessageDTOToEntity(biometricsMessageDTO));
    }
}

