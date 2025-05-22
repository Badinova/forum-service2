package ait.cohort55.accounting.controller;

import ait.cohort55.accounting.dto.RolesDto;
import ait.cohort55.accounting.dto.UserDto;
import ait.cohort55.accounting.dto.UserEditDto;
import ait.cohort55.accounting.dto.UserRegisterDto;
import ait.cohort55.accounting.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class UserAccountController {
    private final UserAccountService userAccountService;

    @PostMapping("/register")
    public UserDto register(@RequestBody UserRegisterDto userRegisterDto) {
        return userAccountService.register(userRegisterDto);
    }

    @PostMapping("/login")
    public UserDto login(Principal principal) {


        return userAccountService.getUser(principal.getName());
    }

    @DeleteMapping("/usre/{login}")
    public UserDto removeUser(@PathVariable String login) {
        return userAccountService.removeUser(login);
    }

    @PatchMapping("/user/{login}")
    public UserDto updateUser(@PathVariable String login, @RequestBody UserEditDto userEditDto) {
        return userAccountService.updateUser(login, userEditDto);
    }

    @PatchMapping("/user/{login}/role/{role}")
    public RolesDto addRole(@PathVariable String login, @PathVariable String role) {
        return userAccountService.changeRoleList(login, role, true);
    }

    @DeleteMapping("/user/{login}/role/{role}")
    public RolesDto deleteRole(@PathVariable String login, @PathVariable String role) {
        return userAccountService.changeRoleList(login, role, false);

    }

    @PatchMapping("/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword(Principal principal, @RequestHeader("X-Password") String newPassword) {
        userAccountService.changePassword(principal.getName(), newPassword);

    }

    @GetMapping("/user/{login}")
    public UserDto getUser(@PathVariable String login) {
        return userAccountService.getUser(login);
    }


}
