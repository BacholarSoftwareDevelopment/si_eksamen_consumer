package dk.si.consumer.files;

import dk.si.consumer.model.Message;
import dk.si.consumer.resource.MessageResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileReadAndWrite {

    private static String filename;
    private static Logger logger = LoggerFactory.getLogger(FileReadAndWrite.class);


    public void writeMessageToFile(String message, Message messageObject) {

        filename = "src/main/resources/messageFiles/messages";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            //writer.append('\n');
            writer.append(message);
            writer.append('\n');
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

    }

    public void writeMessageToFile(String message) {

        filename = "src/main/resources/messageFiles/messages";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            //writer.append('\n');
            writer.append(message);
            writer.append('\n');
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

    }

    public static List<String> readMessageFromFile() throws IOException {

        filename = "src/main/resources/messageFiles/messages";

        List<String> result = new ArrayList<>();
        BufferedReader br = null;

        try {

            br = new BufferedReader(new FileReader(filename));

            String line;
            while ((line = br.readLine()) != null) {
                result.add(line);
            }

        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            if (br != null) {
                br.close();
            }
        }
        return result;
    }

    public static List<String> readMessageFromFile(String userId, String topic) throws IOException {

        filename = "src/main/resources/messageFiles/" + userId + "_" + topic;

        List<String> result = new ArrayList<>();
        BufferedReader br = null;

        try {

            br = new BufferedReader(new FileReader(filename));

            String line;
            while ((line = br.readLine()) != null) {
                result.add(line);
            }

        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            if (br != null) {
                br.close();
            }
        }
        return result;
    }
}
