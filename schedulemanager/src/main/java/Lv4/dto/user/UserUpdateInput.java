package Lv4.dto.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UserUpdateInput {
    private final String name;
    private final String password;
    private final String email;

    @JsonCreator
    public UserUpdateInput(@JsonProperty("name") String name, @JsonProperty("password")String password, @JsonProperty("email")String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }
}
