package AfterLv4.dto.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CommentUpdateInput {
    @NotBlank
    @Size(max=100,message = "댓글은 100자 이하여야 합니다.")
    private final String comment;

    public CommentUpdateInput(String comment) {
        this.comment = comment;
    }
}
