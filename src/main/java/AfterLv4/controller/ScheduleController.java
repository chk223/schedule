package AfterLv4.controller;

import AfterLv4.dto.schedule.ScheduleDisplay;
import AfterLv4.dto.schedule.ScheduleInput;
import AfterLv4.dto.schedule.SchedulePageDisplay;
import AfterLv4.dto.schedule.ScheduleUpdateInput;
import AfterLv4.service.ScheduleService;
import AfterLv4.util.FieldErrorFinder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;

    /**
     * 일정 추가
     * @param scheduleInput 일정 추가 양식
     * @param result
     * @param request
     */
    @PostMapping
    public void addSchedule(@RequestBody @Valid ScheduleInput scheduleInput, BindingResult result, HttpServletRequest request) {
        FieldErrorFinder.isFieldHasError(result);
        scheduleService.addSchedule(scheduleInput, request);
    }

    /**
     * 모든 일정 찾기
     * @param pageable 페이징 양식(page,size,sort)
     * @return 페이징 처리 된 일정 리스트
     */
    @GetMapping("/all")
    public Page<SchedulePageDisplay> findSchedules(@PageableDefault(size=10, sort="updatedAt",direction = Sort.Direction.DESC) Pageable pageable) {
        return scheduleService.findSchedules(pageable);
    }

    /**
     * 특정 id의 일정 정보 찾기
     * @param id 찾고자 하는 일정 id
     * @return 해당 일정 정보
     */
    @GetMapping("/{id}")
    public ScheduleDisplay findScheduleById(@PathVariable Long id) {
        return scheduleService.findScheduleById(id);
    }

    /**
     * 로그인 한 유저가 작성한 일정 찾기
     * @param pageable 페이징 객체(page,size,sort)
     * @param request
     * @return 페이징 된 일정 리스트
     */
    @GetMapping("/mine")
    public Page<SchedulePageDisplay> findMySchedules(@PageableDefault(size=10, sort="updatedAt",direction = Sort.Direction.DESC) Pageable pageable, HttpServletRequest request) {
        return scheduleService.findMySchedules(pageable,request);
    }

    /**
     * 일정 수정
     * @param id 수정하고자 하는 일정 id
     * @param updateInput 수정 양식
     * @param result
     */
    @PutMapping("/{id}")
    public void editSchedule(@PathVariable Long id, @RequestBody @Valid ScheduleUpdateInput updateInput, BindingResult result) {
        FieldErrorFinder.isFieldHasError(result);
        scheduleService.editSchedule(id, updateInput);
    }

    /**
     * 일정 삭제
     * @param id 삭제하고자 하는 일정 id
     */
    @DeleteMapping("/{id}")
    public void removeSchedule(@PathVariable Long id) {
        scheduleService.removeSchedule(id);
    }
}
