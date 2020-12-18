package dk.si.consumer.files;

import dk.si.consumer.model.Message;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileReadAndWrite {

    private static String filename;

    public void writeMessageToFile(String message, Message messageObject) {

        filename = "src/main/resources/messageFiles/" + messageObject.getId() + "_" + messageObject.getTopic();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
        writer.append('\n');
        writer.append(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

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
            e.printStackTrace();
        } finally {
            if (br != null) {
                br.close();
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        List<String> list = readMessageFromFile("1", "tourism");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}

