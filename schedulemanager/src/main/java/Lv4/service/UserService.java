package Lv4.service;

import Lv4.domain.User;
import Lv4.dto.user.UserDeleteInput;
import Lv4.dto.user.UserInput;
import Lv4.dto.user.UserUpdateInput;

import java.util.List;
import java.util.UUID;

public interface UserService {
    void joinUser(UserInput input);
    List<User> findAllUser();
    User findUser(UUID id);
    void editUserInfo(UserUpdateInput updateInput);
    void removeUser(UserDeleteInput deleteInput);
}
