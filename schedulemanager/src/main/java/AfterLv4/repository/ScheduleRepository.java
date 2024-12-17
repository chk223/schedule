package AfterLv4.repository;

import AfterLv4.domain.Schedule;
import AfterLv4.domain.User;
import AfterLv4.dto.schedule.SchedulePageDisplay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query("SELECT s FROM Schedule s JOIN FETCH User u WHERE s.id = :scheduleId AND u.id = :userId")
    Optional<Schedule> findUserAndSchedule(UUID userId, Long scheduleId);
    @EntityGraph(attributePaths = {"user"})
    Optional<Schedule> findById(Long id);
    @EntityGraph(attributePaths = {"user"})
    Page<Schedule> findAll(Pageable pageable);
    @Query(value = "SELECT new AfterLv4.dto.schedule.SchedulePageDisplay(s.id,s.user.name,s.title,s.content,s.createdAt,s.updatedAt,count(c)) " +
            "FROM Schedule s LEFT JOIN Comment c ON c.schedule.id = s.id " +
            "GROUP BY s.id, s.user.name, s.title, s.content, s.createdAt, s.updatedAt")
    Page<SchedulePageDisplay> findAllWithCommentCount(Pageable pageable);
}
