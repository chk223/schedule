package Lv1.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;


@Getter
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue
    private UUID id = UUID.randomUUID();
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;

    public User() {
    }
    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

}
