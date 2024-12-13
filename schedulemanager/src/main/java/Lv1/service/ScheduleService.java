package Lv1.service;

import Lv1.dto.schedule.ScheduleDeleteInput;
import Lv1.dto.schedule.ScheduleDisplay;
import Lv1.dto.schedule.ScheduleInput;
import Lv1.dto.schedule.ScheduleUpdateInput;

import java.util.List;

public interface ScheduleService {
    void addSchedule(ScheduleInput scheduleInput);
    ScheduleDisplay findScheduleById(Long id);
    List<ScheduleDisplay> findSchedules();
    void editSchedule(ScheduleUpdateInput updateInput);
    void removeSchedule(ScheduleDeleteInput deleteInput);
}
