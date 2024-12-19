package AfterLv4.dto.comment;

import lombok.Getter;

@Getter
public class CommentDisplay {
    private final Long id;
    private final Long scheduleId;
    private final String userName;
    private final String content;

    public CommentDisplay(Long id, Long scheduleId, String userName, String content) {
        this.id = id;
        this.scheduleId = scheduleId;
        this.userName = userName;
        this.content = content;
    }
}
