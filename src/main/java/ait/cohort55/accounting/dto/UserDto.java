package ait.cohort55.accounting.dto;

import lombok.*;

import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String login;
    private String firstName;
    private String lastName;
    @Singular
    private Set<String> roles;
}
