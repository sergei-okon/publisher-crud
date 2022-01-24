package ua.com.okon.repository;

import ua.com.okon.model.Post;

import java.io.File;
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

        Post post = posts.stream()
                .filter(postTemp -> Objects.equals(postTemp.getId(), id))
                .findAny().orElse(null);

        if (post == null) {
            System.out.println("Post with id " + id + " not found");
        }
        return post;
    }

    @Override
    public Post save(Post post) {
        List<Post> posts = findAll();

        final Post existingPost = findById(post.getId());

        if (existingPost == null) {
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

        if (findById(id) != null) {
            for (Post postTemp : posts) {
                if (Objects.equals(postTemp.getId(), id)) {
                    postTemp.setContent(post.getContent());
                    postTemp.setCreated(post.getCreated());
                    postTemp.setUpdated(post.getUpdated());
                }
                jsonSource.putJsonToFile(posts);
            }
        } else {
            System.out.println("Writer with id " + id + " not found");
        }
    }
}
