package AfterLv4.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Entity
@Table(name="user")
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @Column(nullable = false)
    @Setter
    private String name;
    @Column(nullable = false)
    @Setter
    private String password;
    @Column(nullable = false)
    @Setter
    private String email;
    @Column(name="joined_at",updatable = false, nullable = false)
    @CreatedDate
    private LocalDateTime joinedAt;

    public User() {
    }
    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

}
