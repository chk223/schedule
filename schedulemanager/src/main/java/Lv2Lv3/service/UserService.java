package Lv2Lv3.service;

import Lv2Lv3.domain.User;
import Lv2Lv3.dto.user.UserDeleteInput;
import Lv2Lv3.dto.user.UserDisplay;
import Lv2Lv3.dto.user.UserInput;
import Lv2Lv3.dto.user.UserUpdateInput;

import java.util.List;
import java.util.UUID;

public interface UserService {
    void joinUser(UserInput input);
    List<UserDisplay> findAllUser();
    UserDisplay findUser(UUID id);
    void editUserInfo(UserUpdateInput updateInput);
    void removeUser(UserDeleteInput deleteInput);
}
