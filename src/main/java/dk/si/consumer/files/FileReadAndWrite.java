package dk.si.consumer.files;

import dk.si.consumer.model.Message;

import java.io.*;

public class FileReadAndWrite {

    private final String HOTEL =   "src/main/resources/hotel_messages.txt";
    private final String Airport = "src/main/resources/airport_messages.txt";
    private final String Tourism = "src/main/resources/tourism_messages.txt";
    private String filename;

    public void writeMessageToFile(String message, String topic) {
        //Write JSON file
        if(topic.equals("hotel")){filename = HOTEL;}
        if(topic.equals("airport")){filename = Airport;}
        if(topic.equals("tourism")){filename = Tourism;}

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
        writer.append('\n');
        writer.append(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void readMessageFromFile() {
        try (FileReader reader = new FileReader(filename))
        {
            System.out.println(reader);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

