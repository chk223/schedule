package AfterLv4.service;

import AfterLv4.config.PasswordEncoder;
import AfterLv4.domain.User;
import AfterLv4.dto.login.LoginRequest;
import AfterLv4.dto.user.UserDisplay;
import AfterLv4.dto.user.UserInput;
import AfterLv4.dto.user.UserUpdateInput;
import AfterLv4.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public User login(LoginRequest loginRequest, HttpServletRequest httpRequest) {
        Optional<User> userOptional = userRepository.findByName(loginRequest.getName());
        if (userOptional.isEmpty()) {
            throw new RuntimeException("아이디가 존재하지 않습니다.");
        }
        User user = userOptional.get();
        // 비밀번호 검증
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
        // 세션 생성
        HttpSession session = httpRequest.getSession();
        session.setAttribute("user", user.getName());
        return user; // 검증 성공한 사용자 반환
    }

    @Override
    public void joinUser(UserInput input) {
        String encodedPassword = passwordEncoder.encode(input.getPassword());
        User user = new User(input.getName(), encodedPassword, input.getEmail());
        userRepository.save(user);
        log.info("가입하려는 id = {}", user.getId());
    }

    @Override
    public List<UserDisplay> findAllUser() {
        return userRepository.findAll().stream()
                .map(user -> new UserDisplay(user.getId(),user.getName(),user.getEmail(),user.getJoinedAt()))
                .toList();
    }

    @Override
    public UserDisplay findUser(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("해당 유저가 존재하지 않습니다. 입력한 id = " + id));
        return new UserDisplay(user.getId(),user.getName(),user.getEmail(),user.getJoinedAt());
    }

    @Override
    @Transactional
    public void editUserInfo(UUID id ,UserUpdateInput updateInput) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 유저가 존재하지 않습니다. 입력한 id = "+id));
        user.setName(updateInput.getName());
        user.setPassword(updateInput.getPassword());
        user.setEmail(updateInput.getEmail());

    }

    @Override
    public void removeUser(UUID id) {
        userRepository.deleteById(id);
    }
}
