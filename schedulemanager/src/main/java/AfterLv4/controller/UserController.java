package AfterLv4.controller;

import AfterLv4.dto.user.UserDisplay;
import AfterLv4.dto.user.UserUpdateInput;
import AfterLv4.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/all")
    public List<UserDisplay> findAllUsers() {
        return userService.findAllUser();
    }

    @GetMapping("/{id}")
    public UserDisplay findUserById(@PathVariable UUID id) {
        return userService.findUser(id);
    }

    @PutMapping("/{id}")
    public void editUserInfo(@PathVariable UUID id,@RequestBody @Valid UserUpdateInput updateInput) {
        log.info("updateInput: {}", updateInput);
        log.info("수정하려는 id = {} name = {}", id, updateInput.getName());
        userService.editUserInfo(id,updateInput);
    }

    @DeleteMapping("/{id}")
    public void removeUser(@PathVariable UUID id) {
        log.info("삭제하려는 id = {}", id);
        userService.removeUser(id);
    }

}
