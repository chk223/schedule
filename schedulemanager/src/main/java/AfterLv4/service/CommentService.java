package AfterLv4.service;

import AfterLv4.domain.Comment;
import AfterLv4.dto.comment.CommentDisplay;
import AfterLv4.dto.comment.CommentInput;
import AfterLv4.dto.comment.CommentUpdateInput;

import java.util.List;

public interface CommentService {
    void addComment(CommentInput input);
    List<CommentDisplay> findCommentsFromSchedule(Long scheduleId);
    void editComment(Long id,CommentUpdateInput updateInput);
    void removeComment(Long id);
}
