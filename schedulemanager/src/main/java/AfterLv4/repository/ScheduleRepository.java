package AfterLv4.repository;

import AfterLv4.domain.Schedule;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @EntityGraph(attributePaths = {"user"})
    Optional<Schedule> findById(Long id);
    @EntityGraph(attributePaths = {"user"})
    List<Schedule> findAll();
}
