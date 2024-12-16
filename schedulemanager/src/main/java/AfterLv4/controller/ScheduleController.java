package AfterLv4.controller;

import AfterLv4.dto.schedule.ScheduleDisplay;
import AfterLv4.dto.schedule.ScheduleInput;
import AfterLv4.dto.schedule.ScheduleUpdateInput;
import AfterLv4.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping
    public void addSchedule(@RequestBody @Valid ScheduleInput scheduleInput) {
        scheduleService.addSchedule(scheduleInput);
    }

    @GetMapping("/all")
    public List<ScheduleDisplay> findSchedules() {
        return scheduleService.findSchedules();
    }

    @GetMapping("/{id}")
    public ScheduleDisplay findScheduleById(@PathVariable Long id) {
        return scheduleService.findScheduleById(id);
    }

    @PutMapping("/{id}")
    public void editSchedule(@PathVariable Long id,@RequestBody @Valid ScheduleUpdateInput updateInput) {
        scheduleService.editSchedule(id, updateInput);
    }
    @DeleteMapping("/{id}")
    public void removeSchedule(@PathVariable Long id) {
        scheduleService.removeSchedule(id);
    }
}
