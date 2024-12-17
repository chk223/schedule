package AfterLv4.repository;

import AfterLv4.domain.Comment;
import AfterLv4.dto.comment.CommentDisplay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT new AfterLv4.dto.comment.CommentDisplay(c.id, c.schedule.id, u.name, c.content) " +
            "FROM Comment c " +
            "JOIN c.user u " +
            "WHERE c.schedule.id = :scheduleId")
    List<CommentDisplay> findCommentDisplayByScheduleId(@Param("scheduleId") Long scheduleId);
    long countByScheduleId(Long scheduleId);
}
