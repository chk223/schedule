package AfterLv4.controller;

import AfterLv4.dto.comment.CommentDisplay;
import AfterLv4.dto.comment.CommentInput;
import AfterLv4.dto.comment.CommentUpdateInput;
import AfterLv4.service.CommentService;
import AfterLv4.util.FieldErrorFinder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * 댓글 추가
     * @param commentInput 댓글 추가 시 필수 입력 값
     * @param result
     * @param request
     */
    @PostMapping
    void addComment(@RequestBody @Valid CommentInput commentInput, BindingResult result, HttpServletRequest request) {
        FieldErrorFinder.isFieldHasError(result);
        commentService.addComment(commentInput, request);
    }

    /**
     * 일정 id를 통해 댓글 조회
     * @param scheduleId 일정  id
     * @return 댓글 리스트
     */
    @GetMapping("/{scheduleId}")
    List<CommentDisplay> displayCommentForSchedule(@PathVariable Long scheduleId) {
        return commentService.findCommentsFromSchedule(scheduleId);
    }

    /**
     * 댓글 수정
     * @param id 수정 할 댓글 id
     * @param updateInput 수정 할 입력 값
     * @param result
     */
    @PutMapping("/{id}")
    void editComment(@PathVariable Long id, @RequestBody @Valid CommentUpdateInput updateInput, BindingResult result) {
        FieldErrorFinder.isFieldHasError(result);
        commentService.editComment(id,updateInput);
    }

    /**
     * 댓글 삭제
     * @param id 삭제 할 댓글 id
     */
    @DeleteMapping("/{id}")
    void removeComment(@PathVariable Long id) {
        commentService.removeComment(id);
    }

}
