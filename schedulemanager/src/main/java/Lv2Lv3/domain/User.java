package Lv2Lv3.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.ZonedDateTime;
import java.util.UUID;


@Getter
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue
    private UUID id = UUID.randomUUID();
    @Column(nullable = false)
    @Setter
    private String name;
    @Column(nullable = false)
    @Setter
    private String password;
    @Column(nullable = false)
    @Setter
    private String email;
    @Column(updatable = false)
    @CreatedDate
    private ZonedDateTime joinedAt;

    public User() {
    }
    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

}
