package ua.com.okon.repository;

import ua.com.okon.model.Writer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JsonWriterRepositoryImpl implements WriterRepository {

    private final JsonSource<Writer> jsonSource;

    public JsonWriterRepositoryImpl() {
        jsonSource = new JsonSource<>(new File("src/main/resources/writers.json"));
    }

    @Override
    public List<Writer> findAll() {
        List<Writer> writers = jsonSource.getJsonFromFile(Writer.class);

        if (writers == null) {
            System.out.println("DB is empty");
            return new ArrayList<>();
        }
        return writers;
    }

    @Override
    public Writer findById(Long id) {
        List<Writer> writers = findAll();

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
        List<Writer> writers = findAll();

        final Writer existingWriter = findById(writer.getId());

        if (existingWriter == null) {
            writers.add(writer);
            jsonSource.putJsonToFile(writers);
            System.out.println("Added writer with id " + writer.getId());

        } else {
            System.out.println("Unable to add writer to database. " +
                    "The writer with id " + writer.getId() + " is already in the database");
            return existingWriter;
        }
        return writer;
    }

    @Override
    public void deleteById(Long id) {
        List<Writer> writers = findAll();

        if (findById(id) != null) {
            writers.removeIf(writer -> Objects.equals(writer.getId(), id));
            jsonSource.putJsonToFile(writers);
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

        List<Writer> writers = findAll();

        if (findById(id) != null) {
            for (Writer writerTemp : writers) {
                if (Objects.equals(writerTemp.getId(), id)) {
                    writerTemp.setFirstName(writer.getFirstName());
                    writerTemp.setLastName(writer.getLastName());
                    writerTemp.setPosts(writer.getPosts());
                }
                jsonSource.putJsonToFile(writers);
            }
        } else {
            System.out.println("Writer with id " + id + " not found");
        }
    }
}
