package AfterLv4.controller;

import AfterLv4.domain.User;
import AfterLv4.dto.login.LoginRequest;
import AfterLv4.dto.login.LoginResponse;
import AfterLv4.dto.user.UserInput;
import AfterLv4.service.UserService;
import AfterLv4.util.FieldErrorFinder;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    /**
     * 회원 가입
     * @param userInput 회원 가입 양식
     * @param result
     */
    @PostMapping("/signup")
    public void signUpUser(@RequestBody @Valid UserInput userInput, BindingResult result) {
        FieldErrorFinder.isFieldHasError(result);
        log.info("가입 정보: name={} password={} email={} ", userInput.getName(), userInput.getPassword(), userInput.getEmail());
        userService.joinUser(userInput);
    }

    /**
     * 로그인
     * @param loginRequest 로그인 양식
     * @param httpRequest
     * @param result
     * @return 로그인 한 유저 id 반환
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest, HttpServletRequest httpRequest, BindingResult result) {
        FieldErrorFinder.isFieldHasError(result);
        try{
            User user = userService.login(loginRequest, httpRequest);
            log.info("로그인 성공!, id = {}",user.getId());
            return ResponseEntity.ok(new LoginResponse(user.getId()));
        } catch (RuntimeException e) {
            log.warn("로그인 실패! error ={}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

    }

    /**
     * 로그아웃
     * @param request
     * @param response
     */
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
