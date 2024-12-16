package AfterLv4.controller;

import AfterLv4.domain.User;
import AfterLv4.dto.login.LoginRequest;
import AfterLv4.dto.login.LoginResponse;
import AfterLv4.dto.user.UserInput;
import AfterLv4.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/auth")
public class LoginController {
    private final UserService userService;
    @PostMapping("/signup")
    public void signUpUser(@RequestBody @Valid UserInput userInput) {
        log.info("가입 정보: name={} password={} email={} ", userInput.getName(), userInput.getPassword(), userInput.getEmail());
        userService.joinUser(userInput);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest, HttpServletRequest httpRequest) {
        Optional<User> userOptional = userService.login(loginRequest);
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            HttpSession session = httpRequest.getSession();
            session.setAttribute("user",loginRequest.getName());
            log.info("로그인 성공!, 사용자 id ={}",user.getId());
            return ResponseEntity.ok(new LoginResponse(user.getId()));
        }
        log.warn("로그인 실패: 잘못된 아이디 또는 비밀번호");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
            log.info("로그아웃 성공!");
        }
        //세션 쿠키 삭제
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0); // 즉시 만료
        response.addCookie(cookie);

        // 캐시 방지
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
    }
}
