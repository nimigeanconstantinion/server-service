package com.example.nserver.rabbitMqProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessagePublisher {

    private final RabbitTemplate rabbitTemplate;
    private final DirectExchange exchange;


    public MessagePublisher(RabbitTemplate rabbitTemplate, DirectExchange exchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;

    }

    public boolean sendMessage(MyMessage message) {
        message.setPriority(7);
        try{
            rabbitTemplate.convertAndSend(exchange.getName(), ProducerConfig.ROUTING_KEY, message);
            System.out.println("Sent message: " + message.toString());
            return true;
        }catch (Exception e){
            return false;
        }



    }
}
