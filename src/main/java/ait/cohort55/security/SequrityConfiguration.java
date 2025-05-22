package ait.cohort55.security;

import ait.cohort55.accounting.model.Role;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

@Configuration
public class SequrityConfiguration {

    @Bean
    SecurityFilterChain getSecurityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/account/register", "/forum/posts/**").permitAll()
                .requestMatchers("/account/user/{login}/role/{role}")
                .hasRole(Role.ADMINISTRATOR.name())
                .requestMatchers(HttpMethod.PATCH, "/account/user/{login}")
                .access(new WebExpressionAuthorizationManager("#login == authentication.name"))
                .requestMatchers(HttpMethod.DELETE, "account/user/{login}")
                .access(new WebExpressionAuthorizationManager("#login == authentication.name or hasRole(Role.ADMINISTRATOR)"))
                .requestMatchers(HttpMethod.POST, "/forum/posts/{autor}")
                .access(new WebExpressionAuthorizationManager("#autor == authentication.name"))
                .requestMatchers(HttpMethod.PATCH, "/forum/post/{id}/comment/{author}")
                .access(new WebExpressionAuthorizationManager("#author == authentication.name"))
                .anyRequest()
                .authenticated());

        return http.build();
    }
}
