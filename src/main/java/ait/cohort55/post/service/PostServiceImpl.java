package ait.cohort55.post.service;

import ait.cohort55.post.dao.PostRepository;
import ait.cohort55.post.dto.NewCommentDto;
import ait.cohort55.post.dto.NewPostDto;
import ait.cohort55.post.dto.PostDto;
import ait.cohort55.post.dto.exeption.PostNotFoundException;
import ait.cohort55.post.model.Comment;
import ait.cohort55.post.model.Post;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public PostDto addNewPost(String author, NewPostDto newpostDto) {
        Post post = modelMapper.map(newpostDto, Post.class);
        post.setAuthor(author);
        post = postRepository.save(post);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto findPostById(String id) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public void addLike(String id) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        post.addLike();
        postRepository.save(post);
    }

    @Override
    public PostDto updatePost(String id, NewPostDto newpostDto) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        String content = newpostDto.getContent();
        if (content != null) {
            post.setContent(content);
        }
        String title = newpostDto.getTitle();
        if (title != null) {
            post.setTitle(title);
        }
        Set<String> tags = newpostDto.getTags();
        if (tags != null) {
            tags.forEach(post::addTag);
        }
        post = postRepository.save(post);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto deletePost(String id) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        postRepository.delete(post);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto addComment(String id, String author, NewCommentDto newcommentDto) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        Comment comment = modelMapper.map(newcommentDto, Comment.class);
        comment.setUser(author);
        post.addComment(comment);
        postRepository.save(post);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public Iterable<PostDto> findPostsByAuthor(String author) {
        return postRepository.findPostByAuthorIgnoreCase(author)
                .map(post -> modelMapper.map(post,PostDto.class))
                .toList();
//        return postRepository.findByAuthorIgnoreCase(author).stream()
//                .map(post -> modelMapper.map(post, PostDto.class))
//                .collect(Collectors.toList());
    }

    @Override
    public Iterable<PostDto> findPostsByTags(List<String> tags) {
//        return postRepository.findByTagsIn(tags).stream()
//                .map(post -> modelMapper.map(post, PostDto.class))
//                .collect(Collectors.toList());
        return postRepository.findPostsByTagsInIgnoreCase(tags)
                .map(post -> modelMapper.map(post, PostDto.class))
                .toList();
   }

    @Override
    public Iterable<PostDto> findPostsByPeriod(LocalDate dateFrom, LocalDate dateTo) {
//        return postRepository.findByDateCreated(dateFrom.atStartOfDay(), dateTo.atTime(LocalTime.MAX)).stream()
//                .map(post -> modelMapper.map(post, PostDto.class))
//                .collect(Collectors.toList());
        return postRepository.findPostsByDateCreatedBetween(dateFrom, dateTo)
                .map(post -> modelMapper.map(post, PostDto.class))
                .toList();

    }
}
