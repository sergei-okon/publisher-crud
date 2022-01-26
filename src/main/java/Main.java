import ua.com.okonsergei.model.Label;
import ua.com.okonsergei.model.Post;
import ua.com.okonsergei.model.Writer;
import ua.com.okonsergei.repository.json.JsonLabelRepositoryImpl;
import ua.com.okonsergei.repository.json.JsonPostRepositoryImpl;
import ua.com.okonsergei.repository.json.JsonSource;
import ua.com.okonsergei.repository.json.JsonWriterRepositoryImpl;
import ua.com.okonsergei.view.MainView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

//        List<Label> labels = new ArrayList<>();
//        Label label = new Label(1L, "Ukraine");
//        labels.add(label);
//
//
//        Long date = LocalDateTime.of(2016, 1, 2, 2, 5)
//                .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
//
//        Post post = new Post(1L, "UkrPost", date, date, labels);
//        List<Post> posts = new ArrayList<>();
//        posts.add(post);
//
//
//        Writer writer1 = new Writer(1L, "Sergei", "okon", posts);
//        Writer writer2 = new Writer(2L, "Svetlana", "Ilieva", posts);
//
//        JsonWriterRepositoryImpl writerRepository = new JsonWriterRepositoryImpl();
////
//        System.out.println("-----------------------------------");
//        writerRepository.save(writer1);
//        writerRepository.save(writer2);
//        System.out.println("-----------------------------------");

//        writerRepository.deleteById(2L);
//        System.out.println("Writer by id " + writerRepository.findById(2L));
//        writerRepository.findById(2L);
//        System.out.println("-----------------------------------");
//
//        System.out.println("FIND ALL");
//        System.out.println(writerRepository.findAll());
//        System.out.println("-----------------------------------");
//
//        Writer updateWriter = new Writer(2L, "Ivan", "Ivanov", posts);
//        writerRepository.update(2L, updateWriter);

//        File file = new File("src/main/resources/writers.json");


//        System.out.println(")))))))))))))))))))))))))))))))))))))))))))))))))))))");
//        List<Writer>list=new ArrayList<>();
//        System.out.println(jsonSource.getJsonFromFile(list, file));
//         List<Writer> jsonFromFile = JsonSource.getJsonFromFile(list, file);
//        System.out.println(jsonFromFile);
//        System.out.println(LocalDateTime.now());
//        System.out.println("P O S T +++++++++++++++++++++++++++++++++++++++++++++");
//        JsonPostRepositoryImpl jsonPostRepository = new JsonPostRepositoryImpl();
//        jsonPostRepository.findAll();
//        System.out.println("POST findAll\n" + jsonPostRepository.findAll());
//        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");

//        Post post1 = new Post(1L, "USA-Tramp coming", 1235668, LocalDateTime.now(), labels);
//        Post post2 = new Post(2L, "Germany-  Gas", LocalDateTime.now(), LocalDateTime.now(), labels);
//        jsonPostRepository.save(post1);
//        jsonPostRepository.save(post2);
//        jsonPostRepository.deleteById(3L);
//        jsonPostRepository.deleteById(2L);
//        Post postUpdate = new Post(1L, "Italy has been closed",  date, date, labels);
//        jsonPostRepository.update(1L, postUpdate);
//        System.out.println("POST findAll\n" + jsonPostRepository.findAll());


//        System.out.println("L A B E L +++++++++++++++++++++++++++++++=");
//        JsonLabelRepositoryImpl jsonLabelRepository = new JsonLabelRepositoryImpl();
//        jsonLabelRepository.findAll();
//        System.out.println("POST findAll\n" + jsonLabelRepository.findAll());
//        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");

//        Label label1 = new Label(1L, "USA");
//        Label label2 = new Label(2L, "Germany ");
//        jsonLabelRepository.save(label1);
//        jsonLabelRepository.save(label2);
//        jsonLabelRepository.deleteById(3L);
//        jsonLabelRepository.deleteById(2L);
//        Label labelUpdate = new Label(1L, "Italy");
//        jsonLabelRepository.update(1L, labelUpdate);

//        System.out.println("POST findAll\n" + jsonLabelRepository.findAll());
//        System.out.println(writerRepository.findAll());

//        JsonSource.incrementId("writerId");


        MainView mainView = new MainView();
        mainView.run();
    }

}
