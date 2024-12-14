package Lv4.dto.user;

import lombok.Getter;

@Getter
public class UserInput {
    private final String name;
    private final String password;
    private final String email;

    public UserInput(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }
}
