package AfterLv4.service;

import AfterLv4.domain.Comment;
import AfterLv4.domain.Schedule;
import AfterLv4.domain.User;
import AfterLv4.dto.comment.CommentDisplay;
import AfterLv4.dto.comment.CommentInput;
import AfterLv4.dto.comment.CommentUpdateInput;
import AfterLv4.repository.CommentRepository;
import AfterLv4.repository.ScheduleRepository;
import AfterLv4.repository.UserRepository;
import AfterLv4.util.EntityFinder;
import AfterLv4.util.UserIdHandlerFromSession;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, ScheduleRepository scheduleRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
    }

    @Transactional //한번에 트랜잭션을 실행하는 것으로 성능 향상 고려함!
    @Override
    public void addComment(CommentInput input, HttpServletRequest request) {
        UUID userId = UserIdHandlerFromSession.getMyIdFromSession(request);
        Schedule schedule = EntityFinder.findByIdOrThrowException(input.getScheduleId(), scheduleRepository, "일정");
        User user = EntityFinder.findByIdOrThrowException(userId, userRepository, "유저");
        Comment comment = new Comment(input.getContent(),user,schedule);
        commentRepository.save(comment);
    }

    @Override
    public List<CommentDisplay> findCommentsFromSchedule(Long scheduleId) {
        List<CommentDisplay> comments = commentRepository.findCommentDisplayByScheduleId(scheduleId);
        if(comments.isEmpty()) throw new EntityNotFoundException("해당 일정에 댓글이 없습니다. scheduleId = " + scheduleId);
        return comments;
    }

    @Override
    @Transactional
    public void editComment(Long id, CommentUpdateInput updateInput) {
        Comment comment = EntityFinder.findByIdOrThrowException(id, commentRepository, "댓글");
        comment.setContent(updateInput.getComment());
    }

    @Override
    public void removeComment(Long id) {
        Comment comment = EntityFinder.findByIdOrThrowException(id, commentRepository, "댓글");
        commentRepository.delete(comment);
    }
}
