package ua.com.okon.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonSource<T> {

    private final static Gson gson = new Gson();
    private final File file;

    public JsonSource(File file) {
        this.file = file;
    }

    public void putJsonToFile(List<T> t) {
        try (FileWriter fileWriter = new FileWriter(file)) {
            gson.toJson(t);
            fileWriter.write(gson.toJson(t));

        } catch (IOException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

    public List<T> getJsonFromFile(Class<T> clazz) {
        List<T> list = new ArrayList<>();

        try (FileReader fileReader = new FileReader(file)) {
            Type founderListType = TypeToken.getParameterized(ArrayList.class, clazz).getType();

            list = gson.fromJson(fileReader, founderListType);

        } catch (IOException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        return list;
    }
}
