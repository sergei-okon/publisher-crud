package ua.com.okonsergei.model;

import ua.com.okonsergei.utils.ConvertLocalDateTime;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.TimeZone;

public class Post {

    private Long id;
    private String content;
    private Long created;
    private Long updated;
    private List<Label> labels;

    public Post() {
    }

    public Post(Long id, String content, Long created, Long updated, List<Label> labels) {
        this.id = id;
        this.content = content;
        this.created = created;
        this.updated = updated;
        this.labels = labels;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public Long getUpdated() {
        return updated;
    }

    public void setUpdated(Long updated) {
        this.updated = updated;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", created=" + ConvertLocalDateTime.convertLongToLocalDateTime(created) +
                ", updated=" + ConvertLocalDateTime.convertLongToLocalDateTime(updated) +
                ", labels=" + labels +
                '}';
    }
}

