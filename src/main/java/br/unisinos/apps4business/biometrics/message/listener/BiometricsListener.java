package br.unisinos.apps4business.biometrics.message.listener;

import br.unisinos.apps4business.biometrics.api.v1.converter.BiometricsConverter;
import br.unisinos.apps4business.biometrics.message.dto.BiometricsMessageDTO;
import br.unisinos.apps4business.biometrics.service.BiometricsService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class BiometricsListener {
    private BiometricsService biometricsService;
    private BiometricsConverter biometricsConverter;
    private Counter counter;

    public BiometricsListener(BiometricsService biometricsService, BiometricsConverter biometricsConverter, MeterRegistry meterRegistry) {
        this.biometricsService = biometricsService;
        this.biometricsConverter = biometricsConverter;
        this.counter = meterRegistry.counter("received.messages");
    }

    @RabbitListener(queues = "queue")
    public void handle(BiometricsMessageDTO biometricsMessageDTO){
        counter.increment();
        biometricsService.saveBiometrics(biometricsConverter.convertMessageDTOToEntity(biometricsMessageDTO));
    }
}

