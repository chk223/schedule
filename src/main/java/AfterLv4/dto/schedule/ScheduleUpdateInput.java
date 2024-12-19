package AfterLv4.dto.schedule;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleUpdateInput {
    @Size(max = 10,message = "제목은 10글자 이내여야 합니다.")
    private final String title;
    private final String content;

    public ScheduleUpdateInput(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
