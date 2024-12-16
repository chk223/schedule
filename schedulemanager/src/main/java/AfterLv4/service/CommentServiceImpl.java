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
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository, ScheduleRepository scheduleRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public void addComment(CommentInput input) {
        User user = userRepository.findById(input.getWriterId())
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 사용자입니다. id = " + input.getWriterId()));
        Schedule schedule = scheduleRepository.findById(input.getScheduleId())
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 일정입니다. id = " + input.getWriterId()));
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
        Comment comment = commentRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("해당 id를 가진 댓글이 없습니다. id = "+id));
        comment.setContent(updateInput.getComment());
    }

    @Override
    public void removeComment(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 id를 가진 댓글이 없습니다. id = " + id));
        commentRepository.delete(comment);
    }
}
