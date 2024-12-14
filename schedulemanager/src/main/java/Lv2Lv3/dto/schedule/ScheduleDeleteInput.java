package Lv2Lv3.dto.schedule;

import lombok.Getter;

@Getter
public class ScheduleDeleteInput {
    private final Long id;

    public ScheduleDeleteInput(Long id) {
        this.id = id;
    }
}
