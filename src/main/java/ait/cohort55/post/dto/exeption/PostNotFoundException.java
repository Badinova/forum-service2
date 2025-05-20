package ait.cohort55.post.dto.exeption;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@NoArgsConstructor
public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(String message) {
      super(message);
    }
}
