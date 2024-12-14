package Lv4.dto.user;

import lombok.Getter;

import java.util.UUID;

@Getter
public class UserDeleteInput {
    private final UUID id;

    public UserDeleteInput(UUID id) {
        this.id = id;
    }
}
