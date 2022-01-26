package ua.com.okonsergei.view;

import ua.com.okonsergei.controller.LabelController;
import ua.com.okonsergei.controller.PostController;
import ua.com.okonsergei.model.Label;
import ua.com.okonsergei.model.Post;
import ua.com.okonsergei.repository.json.JsonSource;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PostView extends BaseView {

    private final PostController postController = new PostController();
    private final LabelController labelController = new LabelController();
    private final Scanner scanner = new Scanner(System.in);


    @Override
    void create() {
        Post post = new Post();
        Long postId = JsonSource.incrementId("postId");

        System.out.println("Input POST content");
        String content = scanner.nextLine();

        System.out.println("Input Label id via ' , ' ");
        scanner.nextLine();
        String label = scanner.nextLine();

        List<Label> labels = new ArrayList<>();

        String[] postArray = label.split(",");

        for (String s : postArray) {
            labels.add(labelController.findById(Long.valueOf(s)));
        }
        post.setId(postId);
        post.setContent(content);
        post.setCreated(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        post.setUpdated(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        post.setLabels(labels);

        postController.save(post);
        System.out.println(Message.SUCCESSFUL_OPERATION.getMessage());
    }

    @Override
    void edit() {
        System.out.println("Editing POST... Input id ");
        Long id = Long.valueOf(scanner.next());

        System.out.println("Input POST new content");
        scanner.nextLine();
        String content = scanner.nextLine();

        System.out.println("Input Label id via ' , ' ");
        String label = scanner.next();

        List<Label> labels = new ArrayList<>();

        String[] postArray = label.split(",");

        for (String s : postArray) {
            labels.add(labelController.findById(Long.valueOf(s)));
        }
        Post updatePost = new Post();
        updatePost.setId(id);
        updatePost.setContent(content);
        updatePost.setUpdated(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        updatePost.setLabels(labels);

        postController.update(id, updatePost);
        System.out.println(Message.SUCCESSFUL_OPERATION.getMessage());
    }

    @Override
    void delete() {
        System.out.println("Deleting POST. Input Id");
        Long id = Long.valueOf(scanner.next());
        postController.deleteById(id);
        System.out.println(Message.SUCCESSFUL_OPERATION.getMessage());
    }

    @Override
    void findAll() {
        System.out.println("Post List");
        postController.findAll().forEach(System.out::println);
    }

    public void showSecondMenu() {
        System.out.println("Post control menu. What do you want to do?");
        System.out.println(Message.SECOND_MENU.getMessage());
        super.showSecondMenu();
    }
}
