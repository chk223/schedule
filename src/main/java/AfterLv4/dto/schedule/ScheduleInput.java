package AfterLv4.dto.schedule;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.UUID;
@Getter
public class ScheduleInput {
    @NotBlank
    @Size(max = 10,message = "제목은 10글자 이내여야 합니다.")
    private final String title;
    @NotBlank
    private final String content;

    public ScheduleInput(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
