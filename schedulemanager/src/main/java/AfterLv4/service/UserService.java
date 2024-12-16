package AfterLv4.service;

import AfterLv4.domain.User;
import AfterLv4.dto.login.LoginRequest;
import AfterLv4.dto.user.UserDisplay;
import AfterLv4.dto.user.UserInput;
import AfterLv4.dto.user.UserUpdateInput;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    User login(LoginRequest loginRequest, HttpServletRequest servletRequest);
    void joinUser(UserInput input);
    List<UserDisplay> findAllUser();
    UserDisplay findUser(UUID id);
    void editUserInfo(UUID id,UserUpdateInput updateInput);
    void removeUser(UUID id);
}
