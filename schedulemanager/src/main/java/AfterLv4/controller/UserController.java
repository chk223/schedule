package AfterLv4.controller;

import AfterLv4.dto.user.UserDisplay;
import AfterLv4.dto.user.UserUpdateInput;
import AfterLv4.service.UserService;
import AfterLv4.util.FieldErrorFinder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    /**
     * 모든 유저 조회
     * @return
     */
    @GetMapping("/all")
    public List<UserDisplay> findAllUsers() {
        return userService.findAllUser();
    }

    /**
     * id로 유저 정보 조회
     * @param id 찾고자 하는 유저 id
     * @return
     */
    @GetMapping("/{id}")
    public UserDisplay findUserById(@PathVariable UUID id) {
        return userService.findUser(id);
    }

    /**
     * 유저 정보 수정
     * @param id 수정하고자 하는 유저 id
     * @param updateInput 수정 양식
     * @param result
     */
    @PutMapping("/{id}")
    public void editUserInfo(@PathVariable UUID id,@RequestBody @Valid UserUpdateInput updateInput, BindingResult result) {
        FieldErrorFinder.isFieldHasError(result);
        log.info("updateInput: {}", updateInput);
        log.info("수정하려는 id = {} name = {}", id, updateInput.getName());
        userService.editUserInfo(id,updateInput);
    }

    /**
     * 유저 삭제(회원 탈퇴)
     * @param id 삭제하고자 하는 유저 id
     */
    @DeleteMapping("/{id}")
    public void removeUser(@PathVariable UUID id) {
        log.info("삭제하려는 id = {}", id);
        userService.removeUser(id);
    }
}
