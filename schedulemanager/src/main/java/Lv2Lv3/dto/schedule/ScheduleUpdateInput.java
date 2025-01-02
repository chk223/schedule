package Lv2Lv3.dto.schedule;

import lombok.Getter;

@Getter
public class ScheduleUpdateInput {
    private final Long id;
    private final String title;
    private final String content;

    public ScheduleUpdateInput(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}