package ua.com.okonsergei.view;

import ua.com.okonsergei.controller.PostController;
import ua.com.okonsergei.controller.WriterController;
import ua.com.okonsergei.model.Post;
import ua.com.okonsergei.model.Writer;
import ua.com.okonsergei.repository.json.JsonSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WriterView extends BaseView {

    private final WriterController writerController = new WriterController();
    private final PostController postController = new PostController();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    void create() {
        Writer writer = new Writer();
        Long writerId = JsonSource.incrementId("writerId");

        System.out.println("Input Writer firstName");
        String firstName = scanner.next();

        System.out.println("Input Writer lastName");
        String lastName = scanner.next();

        System.out.println("Input Posts id via ' , ' ");
        scanner.nextLine();
        String post = scanner.nextLine();

        List<Post> posts = new ArrayList<>();

        String[] postArray = post.split(",");

        for (String s : postArray) {
            posts.add(postController.findById(Long.valueOf(s)));
        }
        writer.setId(writerId);
        writer.setFirstName(firstName);
        writer.setLastName(lastName);
        writer.setPosts(posts);

        writerController.save(writer);
    }

    @Override
    void edit() {
        System.out.println("Editing Writer... Input id ");
        Long id = Long.valueOf(scanner.next());

        System.out.println("Input Writer new first name");
        String firstName = scanner.next();

        System.out.println("Input Writer new last Name");
        String lastName = scanner.next();

        System.out.println("Input Posts id via ' , ' ");
        scanner.nextLine();
        String post = scanner.nextLine();
        List<Post> posts = new ArrayList<>();

        String[] postArray = post.split(",");

        for (String element : postArray) {
            posts.add(postController.findById(Long.valueOf(element)));
        }
        Writer updateWriter = new Writer();

        updateWriter.setId(id);
        updateWriter.setFirstName(firstName);
        updateWriter.setLastName(lastName);
        updateWriter.setPosts(posts);

        writerController.update(id, updateWriter);
        System.out.println(Message.SUCCESSFUL_OPERATION.getMessage());
    }

    @Override
    void delete() {
        System.out.println("Deleting Writer. Input Id");
        Long id = Long.valueOf(scanner.next());
        writerController.deleteById(id);
        System.out.println(Message.SUCCESSFUL_OPERATION.getMessage());
    }

    @Override
    void findAll() {
        System.out.println("Writers List");
        writerController.findAll().forEach(System.out::println);
    }

    @Override
    void showSecondMenu() {
        System.out.println("WRITER control menu. What do you want to do?");
        super.showSecondMenu();
    }
}
