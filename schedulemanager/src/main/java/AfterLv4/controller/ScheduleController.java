package AfterLv4.controller;

import AfterLv4.dto.schedule.ScheduleDisplay;
import AfterLv4.dto.schedule.ScheduleInput;
import AfterLv4.dto.schedule.SchedulePageDisplay;
import AfterLv4.dto.schedule.ScheduleUpdateInput;
import AfterLv4.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

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
    public Page<SchedulePageDisplay> findSchedules(@PageableDefault(size=10, sort="updateAt",direction = Sort.Direction.DESC) Pageable pageable) {
        return scheduleService.findSchedules(pageable);
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
