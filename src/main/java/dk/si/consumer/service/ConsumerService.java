package dk.si.consumer.service;

import com.google.gson.Gson;
import dk.si.consumer.files.FileReadAndWrite;
import dk.si.consumer.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ConsumerService
{
    private static final Logger logger = LoggerFactory.getLogger(ConsumerService.class);
    private Gson jsonParser = new Gson();
    private FileReadAndWrite messageUserData = new FileReadAndWrite();


    @KafkaListener(
            topics = "hotel",
            groupId = "group_id_hotel")
    public void consumeHotel(
            @Payload String message,
            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.OFFSET) int offset) {

        //Message messageObject = jsonParser.fromJson(message, Message.class);
        //messageUserData.writeMessageToFile(message,messageObject);
        messageUserData.writeMessageToFile(message);

        System.out.println("Partition: " + partition + " Topic: " + topic + " Offset: " + offset);
        logger.info("Message [{}:{}:{}:{}] consumed",
                partition,
                topic,
                offset,
                message);
    }

    @KafkaListener(
            topics = "airport",
            groupId = "group_id_airport")
    public void consumeAirport(
            @Payload String message,
            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.OFFSET) int offset) {

        //Message messageObject = jsonParser.fromJson(message, Message.class);
        //messageUserData.writeMessageToFile(message,messageObject);
        messageUserData.writeMessageToFile(message);

        System.out.println("Partition: " + partition + " Topic: " + topic + " Offset: " + offset);
        logger.info("Message [{}:{}:{}:{}] consumed",
                partition,
                topic,
                offset,
                message);
    }

    @KafkaListener(
            topics = "tourism",
            groupId = "group_id_tourism")
    public void consumeTourism(
            @Payload String message,
            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.OFFSET) int offset) {

        //Message messageObject = jsonParser.fromJson(message, Message.class);
        //messageUserData.writeMessageToFile(message,messageObject);
        messageUserData.writeMessageToFile(message);

        System.out.println("Partition: " + partition + " Topic: " + topic + " Offset: " + offset);
        logger.info("Message [{}:{}:{}:{}] consumed",
                partition,
                topic,
                offset,
                message);
    }

}