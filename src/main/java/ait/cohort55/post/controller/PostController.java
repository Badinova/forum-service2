package ait.cohort55.post.controller;

import ait.cohort55.post.dto.NewCommentDto;
import ait.cohort55.post.dto.NewPostDto;
import ait.cohort55.post.dto.PostDto;
import ait.cohort55.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/forum")
public class PostController {
    private final PostService postService;
    @PostMapping("/post/{author}")
    public PostDto addNewPost(@PathVariable String author, @RequestBody NewPostDto newpostDto) {
        return postService.addNewPost(author, newpostDto);
    }

    @GetMapping("/post/{id}")
    public PostDto findPostById(@PathVariable String id) {
        return postService.findPostById(id);
    }

    @PatchMapping("/post/{id}/like")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addLike(@PathVariable String id) {
        postService.addLike(id);

    }

    @PatchMapping("/post/{id}")
    public PostDto updatePost(@PathVariable String id, @RequestBody NewPostDto newpostDto) {
        return postService.updatePost(id, newpostDto);
    }

    @DeleteMapping("/post/{id}")
    public PostDto deletePost(@PathVariable String id) {
        return postService.deletePost(id);
    }

    @PatchMapping("/post/{id}/comment/{author}")
    public PostDto addComment(@PathVariable String id, @PathVariable String author, @RequestBody NewCommentDto newcommentDto) {
        return postService.addComment(id, author, newcommentDto);
    }

    @GetMapping("/posts/author/{author}")
    public Iterable<PostDto> findPostsByAuthor(@PathVariable String author) {
        return postService.findPostsByAuthor(author);
    }

    @GetMapping("/posts/tags")
    public Iterable<PostDto> findPostsByTags(@RequestParam("values") List<String> tags) {
        return postService.findPostsByTags(tags);
    }

    @GetMapping("/posts/period")
    public Iterable<PostDto> findPostsByPeriod(@RequestParam LocalDate dateFrom, @RequestParam LocalDate dateTo) {
        return postService.findPostsByPeriod(dateFrom, dateTo);
    }
}
