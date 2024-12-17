package AfterLv4.service;

import AfterLv4.domain.Comment;
import AfterLv4.domain.Schedule;
import AfterLv4.domain.User;
import AfterLv4.dto.comment.CommentDisplay;
import AfterLv4.dto.comment.CommentInput;
import AfterLv4.dto.comment.CommentUpdateInput;
import AfterLv4.exception.ApiException;
import AfterLv4.exception.ErrorMessage;
import AfterLv4.repository.CommentRepository;
import AfterLv4.repository.ScheduleRepository;
import AfterLv4.util.EntityFinder;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    public CommentServiceImpl(CommentRepository commentRepository, ScheduleRepository scheduleRepository) {
        this.commentRepository = commentRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public void addComment(CommentInput input) {
        Schedule schedule = scheduleRepository.findUserAndSchedule(input.getWriterId(), input.getScheduleId())
                .orElseThrow(() -> {
                    ErrorMessage errorMessage = ErrorMessage.ENTITY_NOT_FOUND;
                    return new ApiException(errorMessage.getMessage(), errorMessage.getStatus());
                });
        User user = schedule.getUser();
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
