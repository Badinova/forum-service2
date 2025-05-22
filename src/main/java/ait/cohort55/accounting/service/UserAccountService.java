package ait.cohort55.accounting.service;

import ait.cohort55.accounting.dto.RolesDto;
import ait.cohort55.accounting.dto.UserDto;
import ait.cohort55.accounting.dto.UserEditDto;
import ait.cohort55.accounting.dto.UserRegisterDto;

public interface UserAccountService {
    UserDto register(UserRegisterDto userRegisterDto);
    UserDto getUser (String login);
    UserDto removeUser (String login);
    UserDto updateUser (String login, UserEditDto userEditDto);
    RolesDto changeRoleList (String login, String role, boolean isAddRole);
    void changePassword (String login, String newPassword);
}
