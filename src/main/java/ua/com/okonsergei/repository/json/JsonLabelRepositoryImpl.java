package ua.com.okonsergei.repository.json;

import ua.com.okonsergei.model.Label;
import ua.com.okonsergei.repository.LabelRepository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JsonLabelRepositoryImpl implements LabelRepository {

    private final JsonSource<Label> jsonSource;

    public JsonLabelRepositoryImpl() {
        jsonSource = new JsonSource<>(new File("src/main/resources/labels.json"));
    }

    @Override
    public List<Label> findAll() {
        List<Label> labels = jsonSource.getJsonFromFile(Label.class);

        if (labels == null) {
            System.out.println("DB is empty");
            return new ArrayList<>();
        }
        return labels;
    }

    @Override
    public Label findById(Long id) {
        List<Label> labels = findAll();
        return labels.stream()
                .filter(labelTemp -> Objects.equals(labelTemp.getId(), id))
                .findAny().orElse(null);
    }

    @Override
    public Label save(Label label) {
        List<Label> labels = findAll();

        final Label existingLabels = findById(label.getId());

        if (existingLabels == null) {
            labels.add(label);
            jsonSource.putJsonToFile(labels);
            System.out.println("Added label with id " + label.getId());

        } else {
            System.out.println("Unable to add label to database. " +
                    "The label with id " + label.getId() + " is already in the database");
            return existingLabels;
        }
        return label;
    }

    @Override
    public void deleteById(Long id) {
        List<Label> labels = findAll();

        if (findById(id) != null) {
            labels.removeIf(label -> Objects.equals(label.getId(), id));
            jsonSource.putJsonToFile(labels);
            System.out.println("Deleted label by id " + id);

        } else {
            System.out.println("Unable to delete label from database. " +
                    "Label with id " + id + " not found");
        }
    }

    @Override
    public void update(Long id, Label label) {
        if (id == null) {
            System.out.println("Id is null. Unable to update label");
        }

        List<Label> labels = findAll();

        if (findById(id) != null) {
            for (Label labelTemp : labels) {
                if (Objects.equals(labelTemp.getId(), id)) {
                    labelTemp.setName(label.getName());
                }
                jsonSource.putJsonToFile(labels);
            }
        } else {
            System.out.println("Labels with id " + id + " not found");
        }
    }
}

