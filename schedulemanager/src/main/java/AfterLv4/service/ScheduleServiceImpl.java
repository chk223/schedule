package AfterLv4.service;

import AfterLv4.domain.Schedule;
import AfterLv4.domain.User;
import AfterLv4.dto.schedule.ScheduleDisplay;
import AfterLv4.dto.schedule.ScheduleInput;
import AfterLv4.dto.schedule.SchedulePageDisplay;
import AfterLv4.dto.schedule.ScheduleUpdateInput;
import AfterLv4.exception.ApiException;
import AfterLv4.exception.ErrorMessage;
import AfterLv4.repository.CommentRepository;
import AfterLv4.repository.ScheduleRepository;
import AfterLv4.repository.UserRepository;
import AfterLv4.util.EntityFinder;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    @Override
    public void addSchedule(ScheduleInput scheduleInput) {
        User user = EntityFinder.findByIdOrThrowException(scheduleInput.getWriterId(),userRepository,"유저");
        Schedule schedule = new Schedule(scheduleInput.getTitle(),scheduleInput.getContent());
        schedule.setUser(user);
        scheduleRepository.save(schedule);
    }

    @Override
    public ScheduleDisplay findScheduleById(Long id) {
        Schedule schedule = EntityFinder.findByIdOrThrowException(id,scheduleRepository,"일정");
        return new ScheduleDisplay(
                schedule.getId(),
                schedule.getUser().getName(),
                schedule.getTitle(),
                schedule.getContent()
        );
    }

    @Override
    public Page<SchedulePageDisplay> findSchedules(Pageable pageable) {
        return scheduleRepository.findAllWithCommentCount(pageable);
    }

    @Override
    public void editSchedule(Long id, ScheduleUpdateInput updateInput) {
        Schedule schedule = EntityFinder.findByIdOrThrowException(id,scheduleRepository,"일정");
        schedule.setTitle(updateInput.getTitle());
        schedule.setContent(updateInput.getContent());

    }

    @Override
    public void removeSchedule(Long id) {
        Schedule schedule = EntityFinder.findByIdOrThrowException(id,scheduleRepository,"일정");
        scheduleRepository.delete(schedule);
    }


}
