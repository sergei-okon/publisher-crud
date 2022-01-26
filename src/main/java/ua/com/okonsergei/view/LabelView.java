package ua.com.okonsergei.view;

import ua.com.okonsergei.controller.LabelController;
import ua.com.okonsergei.model.Label;
import ua.com.okonsergei.repository.json.JsonSource;

import java.util.Scanner;

public class LabelView extends BaseView {

    private final LabelController labelController = new LabelController();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    void create() {
        Label label = new Label();
        Long labelId = JsonSource.incrementId("labelId");

        System.out.println("Input Label name");
        String name = scanner.nextLine();
        label.setId(labelId);
        label.setName(name);
        labelController.save(label);
        System.out.println(Message.SUCCESSFUL_OPERATION.getMessage());
    }

    @Override
    void edit() {
        System.out.println("Editing label... Input id ");
        Long id = Long.valueOf(scanner.next());

        System.out.println("Input Label new name");
        String name = scanner.next();
        Label updateLabel = new Label();
        updateLabel.setId(id);
        updateLabel.setName(name);

        labelController.update(id, updateLabel);
        System.out.println(Message.SUCCESSFUL_OPERATION.getMessage());
    }

    @Override
    void delete() {
        System.out.println("Deleting Label. Input Id");
        Long id = Long.valueOf(scanner.next());
        labelController.deleteById(id);
        System.out.println(Message.SUCCESSFUL_OPERATION.getMessage());
    }

    @Override
    void findAll() {
        System.out.println("Label List");
        labelController.findAll().forEach(System.out::println);
    }

    public void showSecondMenu() {
        System.out.println("LABEL control menu. What do you want to do?");
        System.out.println(Message.SECOND_MENU.getMessage());
        super.showSecondMenu();
    }
}