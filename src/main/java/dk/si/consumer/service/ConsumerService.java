package dk.si.consumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService
{
    private static final Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    @KafkaListener(topics = "1", groupId = "group_id")
    public void consume(String message) {
        System.out.println("Consumed message:" + message);
        logger.info("&&& Message [{}] consumed", message);
    }
}
