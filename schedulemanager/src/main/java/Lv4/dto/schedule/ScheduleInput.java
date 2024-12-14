package Lv4.dto.schedule;

import lombok.Getter;

import java.util.UUID;
@Getter
public class ScheduleInput {
    private final UUID writerId;
    private final String title;
    private final String content;

    public ScheduleInput(UUID writerId, String title, String content) {
        this.writerId = writerId;
        this.title = title;
        this.content = content;
    }
}
