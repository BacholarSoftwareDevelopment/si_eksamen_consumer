package dk.si.consumer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.kafka.annotation.EnableKafka;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EnableKafka
public class Message {
    private Long id;
    private String topic;
    private String name;
    private String city;
    private String message;

}
