package ait.cohort55.post.model;

import ait.cohort55.post.dto.CommentDto;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Document(collection = "posts")
public class Post {
    private String id;
    @Setter
    private String title;
    @Setter
    private String content;
    @Setter
    private String author;
    private LocalDateTime dateCreated = LocalDateTime.now();
    private Set<String> tags = new HashSet<>();
    private int likes;
    private List<Comment> comments = new ArrayList<>();

    public Post(String title, String content, String author, Set<String> tags) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.tags = tags;
    }

    public void addLike(){
        likes++;
    }

    public boolean addTag(String tag) {
        return tags.add(tag);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public boolean removeTag(String tag) {
        return tags.remove(tag);
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
    }

}
