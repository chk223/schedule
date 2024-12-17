package AfterLv4.service;

import AfterLv4.dto.schedule.ScheduleDisplay;
import AfterLv4.dto.schedule.ScheduleInput;
import AfterLv4.dto.schedule.SchedulePageDisplay;
import AfterLv4.dto.schedule.ScheduleUpdateInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ScheduleService {
    void addSchedule(ScheduleInput scheduleInput);
    ScheduleDisplay findScheduleById(Long id);
    Page<SchedulePageDisplay> findSchedules(Pageable pageable);
    void editSchedule(Long id, ScheduleUpdateInput updateInput);
    void removeSchedule(Long id);
}
