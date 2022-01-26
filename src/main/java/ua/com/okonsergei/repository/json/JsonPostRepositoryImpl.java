package ua.com.okonsergei.repository.json;

import ua.com.okonsergei.model.Post;
import ua.com.okonsergei.repository.PostRepository;

import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JsonPostRepositoryImpl implements PostRepository {

    private final JsonSource<Post> jsonSource;

    public JsonPostRepositoryImpl() {
        jsonSource = new JsonSource<>(new File("src/main/resources/posts.json"));
    }

    @Override
    public List<Post> findAll() {
        List<Post> posts = jsonSource.getJsonFromFile(Post.class);

        if (posts == null) {
            System.out.println("DB is empty");
            return new ArrayList<>();
        }
        return posts;
    }

    @Override
    public Post findById(Long id) {
        List<Post> posts = findAll();
        return posts.stream()
                .filter(postTemp -> Objects.equals(postTemp.getId(), id))
                .findAny().orElse(null);
    }

    @Override
    public Post save(Post post) {
        List<Post> posts = findAll();

        final Post existingPost = findById(post.getId());

        if (existingPost == null) {
            Long currentTime = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            post.setCreated(currentTime);
            post.setUpdated(currentTime);

            posts.add(post);
            jsonSource.putJsonToFile(posts);
            System.out.println("Added post with id " + post.getId());

        } else {
            System.out.println("Unable to add post to database. " +
                    "The post with id " + post.getId() + " is already in the database");
            return existingPost;
        }
        return post;
    }

    @Override
    public void deleteById(Long id) {
        List<Post> posts = findAll();

        if (findById(id) != null) {
            posts.removeIf(post -> Objects.equals(post.getId(), id));
            jsonSource.putJsonToFile(posts);
            System.out.println("Deleted post by id " + id);

        } else {
            System.out.println("Unable to delete post from database. " +
                    "Post with id " + id + " not found");
        }
    }

    @Override
    public void update(Long id, Post post) {
        if (id == null) {
            System.out.println("Id is null. Unable to update post");
        }
        List<Post> posts = findAll();
        Long currentTime = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        if (findById(id) != null) {
            for (Post postTemp : posts) {
                if (Objects.equals(postTemp.getId(), id)) {
                    postTemp.setContent(post.getContent());
                    postTemp.setLabels(post.getLabels());
                    postTemp.setUpdated(currentTime);
                }
                jsonSource.putJsonToFile(posts);
            }
        } else {
            System.out.println("Post with id " + id + " not found");
        }
    }
}
