package AfterLv4.service;

import AfterLv4.domain.Schedule;
import AfterLv4.domain.User;
import AfterLv4.dto.schedule.ScheduleDisplay;
import AfterLv4.dto.schedule.ScheduleInput;
import AfterLv4.dto.schedule.SchedulePageDisplay;
import AfterLv4.dto.schedule.ScheduleUpdateInput;
import AfterLv4.repository.CommentRepository;
import AfterLv4.repository.ScheduleRepository;
import AfterLv4.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    @Override
    public void addSchedule(ScheduleInput scheduleInput) {
        User user = userRepository.findById(scheduleInput.getWriterId())
                .orElseThrow(()-> new EntityNotFoundException("존재하지 않는 회원입니다! id= "+scheduleInput.getWriterId()));
        Schedule schedule = new Schedule(scheduleInput.getTitle(),scheduleInput.getContent());
        schedule.setUser(user);

        scheduleRepository.save(schedule);
    }

    @Override
    public ScheduleDisplay findScheduleById(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("해당 id를 가진 일정 객체가 없음! id= " + id));
        return new ScheduleDisplay(
                schedule.getId(),
                schedule.getUser().getName(),
                schedule.getTitle(),
                schedule.getContent()
        );
    }

    @Override
    public Page<SchedulePageDisplay> findSchedules(Pageable pageable) {
        Page<Schedule> schedules = scheduleRepository.findAll(pageable);
        // content만 사용하고 싶은 경우, page 객체가 아니라 List<ScheduleDisplay>를 반환하도록 한 후, 아래의 주석처리 된 부분을 사용하면 된다.
//        return schedules.getContent().stream().map(schedule -> new ScheduleDisplay(schedule.getId(),schedule.getUser().getName(),schedule.getTitle(),schedule.getContent())).toList();
        // pageable 정보가 모두 필요 한 경우, Page<ScheduleDisplay>를 반환하도록 한 후, 아래 주석 처리 된 부분을 사용하면 된다.
        return schedules
                .map(schedule ->{

                    long totalComments = commentRepository.countByScheduleId(schedule.getId());
                            return new SchedulePageDisplay(
                                    schedule.getId(),
                                    schedule.getUser().getName(),
                                    schedule.getTitle(),
                                    schedule.getContent(),
                                    schedule.getCreatedAt(),
                                    schedule.getUpdatedAt(),
                                    totalComments
                            );
                }
                );//page.map 사용(타입 변환)
    }

    @Override
    public void editSchedule(Long id, ScheduleUpdateInput updateInput) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("해당 id를 가진 일정 객체가 없음! id= " + id));
        schedule.setTitle(updateInput.getTitle());
        schedule.setContent(updateInput.getContent());

    }

    @Override
    public void removeSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("해당 id를 가진 일정 객체가 없음! id= " + id));
        scheduleRepository.delete(schedule);
    }
}
