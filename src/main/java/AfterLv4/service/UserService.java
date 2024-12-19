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

    /**
     * 로그인 로직 실행
     * @param loginRequest 로그인 요청 정보(id, password)
     * @param servletRequest 세션 처리를 위한 파라미터
     * @return 로그인 한 유저 객체
     */
    User login(LoginRequest loginRequest, HttpServletRequest servletRequest);

    /**
     * 회원 가입
     * @param input 회원 가입 양식(name, password, email)
     */
    void joinUser(UserInput input);

    /**
     * 모든 유저 정보 반환
     * @return 모든 유저 정보
     */
    List<UserDisplay> findAllUser();

    /**
     * 특정 유저 정보 반환
     * @param id 유저를 분류할 식별자
     * @return 특정 유저 정보
     */
    UserDisplay findUser(UUID id);

    /**
     * 유저 정보 수정
     * @param id 수정할 유저 식별자
     * @param updateInput 수정할 정보(name, password, email)
     */
    void editUserInfo(UUID id,UserUpdateInput updateInput);

    /**
     * 유저 정보 삭제- db에서 on cascade 설정해 주어서 연관 테이블의 필드 모두 삭제됨!
     * @param id 삭제할 유저 식별자
     */
    void removeUser(UUID id);
}
