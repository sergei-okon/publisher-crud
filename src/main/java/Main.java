import ua.com.okon.model.Label;
import ua.com.okon.model.Post;
import ua.com.okon.model.Writer;
import ua.com.okon.repository.JsonWriterRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Label> labels = new ArrayList<>();
        Label label = new Label(1L, "Ukraine");
        labels.add(label);

        Post post = new Post(1L, "UkrPost", 12, 12, labels);
        List<Post> posts = new ArrayList<>();
        posts.add(post);


        Writer writer1 = new Writer(1L, "Sergei", "okon", posts);
        Writer writer2 = new Writer(2L, "Svetlana", "Ilieva", posts);

        JsonWriterRepositoryImpl writerRepository = new JsonWriterRepositoryImpl();

        System.out.println("-----------------------------------");
        writerRepository.save(writer1);
        writerRepository.save(writer2);
        System.out.println("-----------------------------------");

        writerRepository.deleteById(3L);
        System.out.println("Writer by id " + writerRepository.findById(2L));
        writerRepository.findById(2L);
        System.out.println("-----------------------------------");

        System.out.println("FIND ALL");
        writerRepository.findAll().forEach(System.out::println);
        System.out.println("-----------------------------------");

        Writer updateWriter = new Writer(2L, "Ivan", "Ivanov", posts);
        writerRepository.update(2L, updateWriter);
    }
}
