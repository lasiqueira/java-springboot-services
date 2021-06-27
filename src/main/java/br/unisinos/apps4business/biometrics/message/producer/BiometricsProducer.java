package br.unisinos.apps4business.biometrics.message.producer;

import br.unisinos.apps4business.biometrics.message.dto.BiometricsMessageDTO;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class BiometricsProducer {
    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;
    private final Counter counter;
    private final Logger logger;

    public BiometricsProducer(RabbitTemplate rabbitTemplate, Queue queue, MeterRegistry meterRegistry) {
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
        this.counter = meterRegistry.counter("sent.messages");
        this.logger = LoggerFactory.getLogger(BiometricsProducer.class);
    }

    public void sendMessage(BiometricsMessageDTO biometricsMessageDTO){
        logger.debug("Sending message to broker");
        logger.debug("biometricsMessageDTO= {}", biometricsMessageDTO);
        counter.increment();
        rabbitTemplate.convertAndSend(queue.getName(),biometricsMessageDTO);
    }
}


