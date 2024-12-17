package AfterLv4.domain;

import AfterLv4.entity.BaseDateEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyGroup;

import java.util.List;

@Entity
@Getter
@Table(name="schedule")
public class Schedule extends BaseDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @ManyToOne
    @JoinColumn(name="writer_id")
    private User user;
    @Column(nullable = false)
    @Setter
    private String title;
    @Column(nullable = false)
    @Setter
    private String content;
    public Schedule() {
    }

    public Schedule(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
