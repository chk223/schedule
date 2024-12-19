package Lv2Lv3.dto.user;

import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class UserDisplay {
    private final String name;
    private final String email;
    private final ZonedDateTime joinedAt;

    public UserDisplay(String name, String email, ZonedDateTime joinedAt) {
        this.name = name;
        this.email = email;
        this.joinedAt = joinedAt;
    }
}
