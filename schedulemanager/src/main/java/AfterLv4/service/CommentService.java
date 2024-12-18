package AfterLv4.service;

import AfterLv4.dto.comment.CommentDisplay;
import AfterLv4.dto.comment.CommentInput;
import AfterLv4.dto.comment.CommentUpdateInput;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface CommentService {
    /**
     * 댓글 작성
     * @param input 댓글 작성 양식(content, writerId, scheduleId)
     */
    void addComment(CommentInput input, HttpServletRequest request);

    /**
     * 일정의 모든 댓글 조회
     * @param scheduleId 댓글을 조회 할 일정 id
     * @return 일정의 모든 댓글 정보
     */
    List<CommentDisplay> findCommentsFromSchedule(Long scheduleId);

    /**
     * 댓글 수정
     * @param id 수정 할 댓글 식별자
     * @param updateInput 수정 할 양식(content)
     */
    void editComment(Long id,CommentUpdateInput updateInput);

    /**
     * 댓글 삭제- db에서 on cascade 설정해 주어서 연관 테이블의 필드 모두 삭제됨!
     * @param id 삭제 할 댓글 식별자
     */
    void removeComment(Long id);
}
