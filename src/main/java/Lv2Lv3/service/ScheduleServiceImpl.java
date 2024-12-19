package Lv2Lv3.service;

import Lv2Lv3.domain.Schedule;
import Lv2Lv3.domain.User;
import Lv2Lv3.dto.schedule.ScheduleDeleteInput;
import Lv2Lv3.dto.schedule.ScheduleDisplay;
import Lv2Lv3.dto.schedule.ScheduleInput;
import Lv2Lv3.dto.schedule.ScheduleUpdateInput;
import Lv2Lv3.repository.ScheduleRepository;
import Lv2Lv3.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

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
                schedule.getUser().getName(),
                schedule.getTitle(),
                schedule.getContent()
        );
    }

    @Override
    public List<ScheduleDisplay> findSchedules() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return schedules.stream()
                .map(schedule -> new ScheduleDisplay(schedule.getUser().getName(),schedule.getTitle(),schedule.getContent()))
                .toList();
    }

    @Override
    public void editSchedule(ScheduleUpdateInput updateInput) {
        Schedule schedule = scheduleRepository.findById(updateInput.getId())
                .orElseThrow(()-> new EntityNotFoundException("해당 id를 가진 일정 객체가 없음! id= " + updateInput.getId()));
        schedule.setTitle(updateInput.getTitle());
        schedule.setContent(updateInput.getContent());

    }

    @Override
    public void removeSchedule(ScheduleDeleteInput deleteInput) {
        if(!scheduleRepository.existsById(deleteInput.getId())) {
            throw new EntityNotFoundException("해당 id를 가진 객체가 없음! id= " + deleteInput.getId());
        }
        scheduleRepository.deleteById(deleteInput.getId());
    }
}
