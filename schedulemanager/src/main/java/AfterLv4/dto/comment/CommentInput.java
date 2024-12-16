package AfterLv4.dto.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CommentInput {
    @NotBlank
    @Size(max=100,message = "댓글은 100자 이하여야 합니다.")
    private final String content;
    private final UUID writerId;
    private final Long scheduleId;

    public CommentInput(String content, UUID writerId, Long scheduleId) {
        this.content = content;
        this.writerId = writerId;
        this.scheduleId = scheduleId;
    }
}
