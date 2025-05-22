package ait.cohort55.post.dao;

import ait.cohort55.post.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

public interface PostRepository extends MongoRepository<Post, String> {
    Stream<Post> findPostByAuthorIgnoreCase(String author);

Stream<Post> findPostsByTagsInIgnoreCase(List<String> tag);

Stream<Post> findPostsByDateCreatedBetween(LocalDate from, LocalDate to);


//    List<Post> findByAuthorIgnoreCase(String author);
//
//    List<Post> findByTagsIn(List<String> tags);
//
//    List<Post> findByDateCreated(LocalDateTime from, LocalDateTime to);
}
