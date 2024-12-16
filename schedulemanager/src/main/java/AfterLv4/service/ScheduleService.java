package AfterLv4.service;

import AfterLv4.dto.schedule.ScheduleDisplay;
import AfterLv4.dto.schedule.ScheduleInput;
import AfterLv4.dto.schedule.ScheduleUpdateInput;

import java.util.List;

public interface ScheduleService {
    void addSchedule(ScheduleInput scheduleInput);
    ScheduleDisplay findScheduleById(Long id);
    List<ScheduleDisplay> findSchedules();
    void editSchedule(Long id, ScheduleUpdateInput updateInput);
    void removeSchedule(Long id);
}
