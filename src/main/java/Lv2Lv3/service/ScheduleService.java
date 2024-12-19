package Lv2Lv3.service;

import Lv2Lv3.dto.schedule.ScheduleDeleteInput;
import Lv2Lv3.dto.schedule.ScheduleDisplay;
import Lv2Lv3.dto.schedule.ScheduleInput;
import Lv2Lv3.dto.schedule.ScheduleUpdateInput;

import java.util.List;

public interface ScheduleService {
    void addSchedule(ScheduleInput scheduleInput);
    ScheduleDisplay findScheduleById(Long id);
    List<ScheduleDisplay> findSchedules();
    void editSchedule(ScheduleUpdateInput updateInput);
    void removeSchedule(ScheduleDeleteInput deleteInput);
}
