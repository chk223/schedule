package Lv1.controller;

import Lv1.domain.Schedule;
import Lv1.dto.schedule.ScheduleDeleteInput;
import Lv1.dto.schedule.ScheduleDisplay;
import Lv1.dto.schedule.ScheduleInput;
import Lv1.dto.schedule.ScheduleUpdateInput;
import Lv1.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping
    public void addSchedule(@RequestBody ScheduleInput scheduleInput) {
        scheduleService.addSchedule(scheduleInput);
    }

    @GetMapping("/all")
    public List<ScheduleDisplay> findSchedules() {
        return scheduleService.findSchedules();
    }

    @GetMapping
    public ScheduleDisplay findScheduleById(Long id) {
        return scheduleService.findScheduleById(id);
    }

    @PutMapping()
    public void editSchedule(@RequestBody ScheduleUpdateInput updateInput) {
        scheduleService.editSchedule(updateInput);
    }
    //put, delete는  @pathvariable로 사용하는 것이 적합하나, 확장성을 위해 dto에 담아서 보냈는데.. 재확인 해보자 나중에.
    @DeleteMapping
    public void removeSchedule(@RequestBody ScheduleDeleteInput deleteInput) {
        scheduleService.removeSchedule(deleteInput);
    }
}
