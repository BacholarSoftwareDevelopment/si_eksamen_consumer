package dk.si.consumer.resource;

import com.google.gson.Gson;
import dk.si.consumer.files.FileReadAndWrite;
import dk.si.consumer.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/kafka")
public class MessageResource {

    private FileReadAndWrite messageUserData = new FileReadAndWrite();
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

    @GetMapping("/message")
    public List<Message> getMessageByIdAndTopic() throws IOException {
        //String userId = "1";
        //String topic = "hotel";

        List<String> stringList = messageUserData.readMessageFromFile();
        List<Message> messageList = transformStringToMessageObject(stringList);

        logger.info("### Consumer got message [{}:{}:{}]",
                //logger.info("### Consumer got message [{}:{}:{}:{}:{}]",
                //userId,
                //topic,
                messageList.size() + 1,
                messageList.get(0).getId(),
                messageList.get(0).getName());

        return messageList;
    }

    public List<Message> transformStringToMessageObject(List<String> stringList) throws IOException {
        String infoFromHotelAPI = getJsonFromHotelAPI();
        List<Message> messageList = new ArrayList<>();
        for (String m: stringList) {
            Message message = jsonParser.fromJson(m, Message.class);
            if(message.getTopic().equals("hotel")){
                message.setMessage(message.getMessage() + "\n\n" + "Information from WEB - VacantRooms at following hotels:" + "\n" + infoFromHotelAPI);
            }
            messageList.add(message);
        }
        return messageList;
    }

    public String getJsonFromHotelAPI() throws IOException {
        URL url = new URL("http://localhost:9080/hotels/hotels");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json;charset=UTF-8");
        con.setRequestProperty("User-Agent", "server");
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()))){
            while((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line);
            }
        }
        String jsonStr = stringBuilder.toString();

        return jsonStr;
    }

}
