package Lv4.dto.user;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class UserDisplay {
    private UUID id;
    private final String name;
    private final String email;
    private final LocalDateTime joinedAt;

    public UserDisplay(UUID id,String name, String email, LocalDateTime joinedAt) {
        this.id=id;
        this.name = name;
        this.email = email;
        this.joinedAt = joinedAt;
    }
}
