package dk.si.consumer.resource;

import com.google.gson.Gson;
import dk.si.consumer.files.FileReadAndWrite;
import dk.si.consumer.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/kafka")
public class MessageResource {

    private FileReadAndWrite messageUserData;
    private Gson jsonParser = new Gson();
    private static Logger logger = LoggerFactory.getLogger(MessageResource.class);


    @GetMapping("/message/{userId}/{topic}")
    public List<Message> getMessageByIdAndTopic(@PathVariable("userId") String userId, @PathVariable("topic") String topic) throws IOException {

        List<String> stringList = messageUserData.readMessageFromFile(userId, topic);
        List<Message> messageList = transformStringToMessageObject(stringList);
        
        logger.info("### Consumer got message [{}:{}:{}:{}:{}]",
                userId,
                topic,
                messageList.size() + 1,
                messageList.get(0).getId(),
                messageList.get(0).getName());

        return messageList;
    }
    
    public List<Message> transformStringToMessageObject(List<String> stringList) {
        List<Message> messageList = new ArrayList<>();
        for (String m: stringList) {
            messageList.add(jsonParser.fromJson(m, Message.class));
        }
        return messageList;
    }
}
