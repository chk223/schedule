package Lv4.dto.schedule;


import lombok.Getter;

@Getter
public class ScheduleDisplay {
    private final String userName;
    private final String title;
    private final String content;

    public ScheduleDisplay(String userName, String title, String content) {
        this.userName = userName;
        this.title = title;
        this.content = content;
    }
}
