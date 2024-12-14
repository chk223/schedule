package Lv4.service;

import Lv4.dto.schedule.ScheduleDeleteInput;
import Lv4.dto.schedule.ScheduleDisplay;
import Lv4.dto.schedule.ScheduleInput;
import Lv4.dto.schedule.ScheduleUpdateInput;

import java.util.List;

public interface ScheduleService {
    void addSchedule(ScheduleInput scheduleInput);
    ScheduleDisplay findScheduleById(Long id);
    List<ScheduleDisplay> findSchedules();
    void editSchedule(ScheduleUpdateInput updateInput);
    void removeSchedule(ScheduleDeleteInput deleteInput);
}
