package Lv4.service;

import Lv4.domain.User;
import Lv4.dto.user.UserDeleteInput;
import Lv4.dto.user.UserInput;
import Lv4.dto.user.UserUpdateInput;
import Lv4.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void joinUser(UserInput input) {
        User user = new User(input.getName(), input.getPassword(), input.getEmail());
        userRepository.save(user);
    }

    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User findUser(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("해당 유저가 존재하지 않습니다. 입력한 id = "+id));
    }

    @Override
    public void editUserInfo(UserUpdateInput updateInput) {
        User user = userRepository.findById(updateInput.getId())
                .orElseThrow(() -> new EntityNotFoundException("해당 유저가 존재하지 않습니다. 입력한 id = "+updateInput.getId()));
        user.setName(updateInput.getName());
        user.setPassword(updateInput.getPassword());
        user.setEmail(updateInput.getEmail());
    }

    @Override
    public void removeUser(UserDeleteInput deleteInput) {
        userRepository.deleteById(deleteInput.getId());
    }
}
