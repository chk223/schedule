package Lv2Lv3.controller;

import Lv2Lv3.dto.user.UserDeleteInput;
import Lv2Lv3.dto.user.UserDisplay;
import Lv2Lv3.dto.user.UserInput;
import Lv2Lv3.dto.user.UserUpdateInput;
import Lv2Lv3.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping
    public void registUser(@RequestBody UserInput userInput) {
        userService.joinUser(userInput);
    }

    @GetMapping("/all")
    public List<UserDisplay> findAllUsers() {
        return userService.findAllUser();
    }

    @GetMapping("/{id}")
    public UserDisplay findUserById(@PathVariable UUID id) {
        return userService.findUser(id);
    }

    @PutMapping
    public void editUserInfo(UserUpdateInput updateInput) {
        userService.editUserInfo(updateInput);
    }

    @DeleteMapping
    public void removeUser(UserDeleteInput deleteInput) {
        userService.removeUser(deleteInput);
    }

}
