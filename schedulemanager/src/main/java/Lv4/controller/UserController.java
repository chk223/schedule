package Lv4.controller;

import Lv4.dto.user.UserDeleteInput;
import Lv4.dto.user.UserDisplay;
import Lv4.dto.user.UserInput;
import Lv4.dto.user.UserUpdateInput;
import Lv4.service.UserService;
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

    @PutMapping
    public void editUserInfo(@RequestBody UserUpdateInput updateInput) {
        log.info("updateInput: {}", updateInput);
        log.info("수정하려는 id = {} name = {}", updateInput.getId(), updateInput.getName());
        userService.editUserInfo(updateInput);
    }

    @DeleteMapping
    public void removeUser(@RequestBody UserDeleteInput deleteInput) {
        log.info("삭제하려는 id = {}", deleteInput.getId());
        userService.removeUser(deleteInput);
    }

}
