package ait.cohort55.post.dao;

import ait.cohort55.post.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {
    List<Post> findByAuthorIgnoreCase(String author);

    List<Post> findByTagsIn(List<String> tags);

    List<Post> findByDateCreated(LocalDateTime from, LocalDateTime to);
}
