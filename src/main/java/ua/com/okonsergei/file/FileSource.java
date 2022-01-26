package ua.com.okonsergei.file;

import java.io.FileWriter;
import java.io.IOException;

public class FileSource {
    FileWriter fileWriter;

    {
        try {
            fileWriter = new FileWriter("src/main/resources/writers.json");
        } catch (IOException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }
}
