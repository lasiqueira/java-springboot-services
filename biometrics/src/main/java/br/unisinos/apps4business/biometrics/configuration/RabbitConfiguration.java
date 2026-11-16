package br.unisinos.apps4business.biometrics.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {
    @Bean
    public Queue queue() {
        return new Queue("queue");
    }

    /**
     * Messages are sent as JSON. Java serialization (the previous default) is rejected by
     * Spring AMQP 4 unless classes are explicitly allow-listed, and JSON keeps the queue
     * readable by non-Java consumers. Spring Boot wires this bean into RabbitTemplate and
     * the listener containers; listeners infer the target type from the method parameter.
     */
    @Bean
    public MessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }
}
