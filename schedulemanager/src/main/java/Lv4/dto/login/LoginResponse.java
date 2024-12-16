package Lv4.dto.login;

import lombok.Getter;

import java.util.UUID;

@Getter
public class LoginResponse {
    private final UUID uuid;

    public LoginResponse(UUID uuid) {
        this.uuid = uuid;
    }
}
