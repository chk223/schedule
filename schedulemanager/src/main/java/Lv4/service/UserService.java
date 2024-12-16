package Lv4.service;

import Lv4.domain.User;
import Lv4.dto.login.LoginRequest;
import Lv4.dto.user.UserDeleteInput;
import Lv4.dto.user.UserDisplay;
import Lv4.dto.user.UserInput;
import Lv4.dto.user.UserUpdateInput;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    Optional<User> login(LoginRequest loginRequest);
    void joinUser(UserInput input);
    List<UserDisplay> findAllUser();
    UserDisplay findUser(UUID id);
    void editUserInfo(UserUpdateInput updateInput);
    void removeUser(UserDeleteInput deleteInput);
}
