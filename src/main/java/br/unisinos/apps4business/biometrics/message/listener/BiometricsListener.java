package br.unisinos.apps4business.biometrics.message.listener;

import br.unisinos.apps4business.biometrics.api.v1.converter.BiometricsConverter;
import br.unisinos.apps4business.biometrics.message.dto.BiometricsMessageDTO;
import br.unisinos.apps4business.biometrics.service.BiometricsService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class BiometricsListener {
    private BiometricsService biometricsService;
    private BiometricsConverter biometricsConverter;
    private Counter counter;
    private Logger logger;

    public BiometricsListener(BiometricsService biometricsService, BiometricsConverter biometricsConverter, MeterRegistry meterRegistry) {
        this.biometricsService = biometricsService;
        this.biometricsConverter = biometricsConverter;
        this.counter = meterRegistry.counter("received.messages");
        this.logger = LoggerFactory.getLogger(BiometricsListener.class);
    }

    @RabbitListener(queues = "queue")
    public void handle(BiometricsMessageDTO biometricsMessageDTO){
        logger.debug("Reading message from broker");
        logger.debug("biometricsMessageDTO= {}", biometricsMessageDTO);

        counter.increment();
        biometricsService.saveBiometrics(biometricsConverter.convertMessageDTOToEntity(biometricsMessageDTO));
    }
}

