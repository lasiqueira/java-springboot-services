package br.unisinos.apps4business.biometrics.message.producer;

import br.unisinos.apps4business.biometrics.message.dto.BiometricsMessageDTO;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class BiometricsProducer {
    private RabbitTemplate rabbitTemplate;
    private Queue queue;
    private Counter counter;

    public BiometricsProducer(RabbitTemplate rabbitTemplate, Queue queue, MeterRegistry meterRegistry) {
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
        this.counter = meterRegistry.counter("sent.messages");
    }

    public void sendMessage(BiometricsMessageDTO biometricsMessageDTO){
        counter.increment();
        rabbitTemplate.convertAndSend(queue.getName(),biometricsMessageDTO);
    }
}


