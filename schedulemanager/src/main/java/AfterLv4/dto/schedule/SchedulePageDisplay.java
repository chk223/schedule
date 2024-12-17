package AfterLv4.dto.schedule;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SchedulePageDisplay {
    private final Long id;
    private final String userName;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final long totalComment;

    public SchedulePageDisplay(Long id, String userName, String title, String content, LocalDateTime createdAt, LocalDateTime updatedAt, long totalComment) {
        this.id = id;
        this.userName = userName;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.totalComment = totalComment;
    }
}
