package AfterLv4.service;

import AfterLv4.dto.schedule.ScheduleDisplay;
import AfterLv4.dto.schedule.ScheduleInput;
import AfterLv4.dto.schedule.SchedulePageDisplay;
import AfterLv4.dto.schedule.ScheduleUpdateInput;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ScheduleService {
    /**
     * 일정 추가
     * @param scheduleInput 추가 할 일정 양식(writerId, title, content)
     */
    void addSchedule(ScheduleInput scheduleInput, HttpServletRequest request);

    /**
     * 특정 일정 정보 조회
     * @param id 일정 식별자
     * @return 특정 일정 정보
     */
    ScheduleDisplay findScheduleById(Long id);

    /**
     * 모든 일정을 페이징을 적용하여 조회
     * @param pageable 페이징 변수(page,size,sort) 입력하지 않은 경우 default 값 적용
     * @return 페이징 된 일정
     */
    Page<SchedulePageDisplay> findSchedules(Pageable pageable);

    /**
     * 현재 로그인 한 유저가 작성한 일정 조회 - 페이징 적용
     * @param pageable 페이징 변수(page,size,sort) 입력하지 않은 경우 default 값 적용
     * @param request 세션 값 가져오기 위한 HttpServletRequest
     * @return 페이징 된 일정
     */
    Page<SchedulePageDisplay> findMySchedules(Pageable pageable, HttpServletRequest request);

    /**
     * 일정 수정
     * @param id 수정 할 일정 식별자
     * @param updateInput 수정 할 양식(title,content)
     */
    void editSchedule(Long id, ScheduleUpdateInput updateInput);

    /**
     * 일정 삭제 - db에서 on cascade 설정해 주어서 연관 테이블의 필드 모두 삭제됨!
     * @param id 삭제 할 일정 식별자
     */
    void removeSchedule(Long id);
}
