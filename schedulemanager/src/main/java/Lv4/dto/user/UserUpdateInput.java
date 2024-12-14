package Lv4.dto.user;

import lombok.Getter;

import java.util.UUID;

@Getter
public class UserUpdateInput {
    private final UUID id;
    private final String name;
    private final String password;
    private final String email;

    public UserUpdateInput(UUID id, String name, String password, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
    }
}
