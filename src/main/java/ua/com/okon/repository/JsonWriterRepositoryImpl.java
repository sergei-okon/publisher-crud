package ua.com.okon.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ua.com.okon.model.Writer;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JsonWriterRepositoryImpl implements WriterRepository {

    private final static File writersJson = new File("src/main/resources/writers.json");
    private final static Gson gson = new Gson();

    @Override
    public List<Writer> findAll() {
        List<Writer> writers = new ArrayList<>();

        try (FileReader fileReader = new FileReader(writersJson)) {
            Type founderListType = new TypeToken<ArrayList<Writer>>() {
            }.getType();

            writers = gson.fromJson(fileReader, founderListType);

        } catch (IOException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        return writers;
    }

    @Override
    public Writer findById(Long id) {
        List<Writer> writers = getListWritersFromFile();

        Writer writer = writers.stream()
                .filter(writerTemp -> Objects.equals(writerTemp.getId(), id))
                .findAny().orElse(null);

        if (writer == null) {
            System.out.println("Writer with id " + id + " not found");
        }
        return writer;
    }

    @Override
    public Writer save(Writer writer) {
        List<Writer> writers = getListWritersFromFile();

        final Writer existingWriter = findById(writer.getId());
        if (existingWriter == null) {
            writers.add(writer);
            putJsonToFile(writers);
        } else {
            System.out.println("Unable to add writer to database. " +
                    "The writer with id " + writer.getId() + " is already in the database");
            return existingWriter;
        }
        return writer;
    }

    @Override
    public void deleteById(Long id) {
        List<Writer> writers = getListWritersFromFile();

        if (findById(id) != null) {
            writers.removeIf(writer -> Objects.equals(writer.getId(), id));
            putJsonToFile(writers);
            System.out.println("Deleted writer by id " + id);

        } else {
            System.out.println("Unable to delete writer from database. " +
                    "Writer with id " + id + " not found");
        }
    }

    @Override
    public void update(Long id, Writer writer) {
        if (id == null) {
            System.out.println("Id is null. Unable to update writer");
        }

        List<Writer> writers = getListWritersFromFile();

        if (findById(id) != null) {
            for (Writer writerTemp : writers) {
                if (Objects.equals(writerTemp.getId(), id)) {
                    writerTemp.setFirstName(writer.getFirstName());
                    writerTemp.setLastName(writer.getLastName());
                    writerTemp.setPosts(writer.getPosts());
                }
                putJsonToFile(writers);
            }
        } else {
            System.out.println("Writer with id " + id + " not found");
        }
    }

    private void putJsonToFile(List<Writer> writers) {
        try (FileWriter fileWriter = new FileWriter(writersJson)) {
            gson.toJson(writers);
            fileWriter.write(gson.toJson(writers));

        } catch (IOException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

    private List<Writer> getListWritersFromFile() {
        List<Writer> writers;

        if (findAll() == null) {
            System.out.println("DB is EMPTY");
            writers = new ArrayList<>();
        } else {
            writers = findAll();
        }
        return writers;
    }
}
