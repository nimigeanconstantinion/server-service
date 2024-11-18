package com.example.nserver.rabbitMqProducer;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerConfig {
    public static final String EXCHANGE_NAME = "my.direct.exchange";
    public static final String ROUTING_KEY_ONE = "routing.key.one";
    public static final String ROUTING_KEY_TWO = "routing.key.two";
    public static final String ROUTING_KEY_THREE = "routing.key.three";

    public static final String QUEUE_ONE = "queue.one";
    public static final String QUEUE_TWO = "queue.two";

    public static final String QUEUE_THREE = "queue.three";

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue queueOne() {
        return new Queue(QUEUE_ONE, true);
    }

    @Bean
    public Queue queueTwo() {
        return new Queue(QUEUE_TWO, true);
    }

    @Bean
    public Queue queueThree() {
        return new Queue(QUEUE_THREE, true);
    }
    @Bean
    public Binding bindingOne(Queue queueOne, DirectExchange exchange) {
        return BindingBuilder.bind(queueOne).to(exchange).with(ROUTING_KEY_ONE);
    }

    @Bean
    public Binding bindingTwo(Queue queueTwo, DirectExchange exchange) {
        return BindingBuilder.bind(queueTwo).to(exchange).with(ROUTING_KEY_TWO);
    }


    @Bean
    public Binding bindingThree(Queue queueThree, DirectExchange exchange) {
        return BindingBuilder.bind(queueThree).to(exchange).with(ROUTING_KEY_THREE);
    }
    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter jsonMessageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter);
        return rabbitTemplate;
    }
}