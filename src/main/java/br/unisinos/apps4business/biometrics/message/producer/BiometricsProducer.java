package br.unisinos.apps4business.biometrics.message.producer;

import br.unisinos.apps4business.biometrics.message.dto.BiometricsMessageDTO;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class BiometricsProducer {
    private RabbitTemplate rabbitTemplate;
    private Queue queue;

    public BiometricsProducer(RabbitTemplate rabbitTemplate, Queue queue) {
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
    }

    public void sendMessage(BiometricsMessageDTO biometricsMessageDTO){

        rabbitTemplate.convertAndSend(queue.getName(),biometricsMessageDTO);
    }
}


