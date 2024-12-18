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

    @PostMapping
    void addComment(@RequestBody @Valid CommentInput commentInput, BindingResult result, HttpServletRequest request) {
        FieldErrorFinder.isFieldHasError(result);
        commentService.addComment(commentInput, request);
    }

    @GetMapping("/{scheduleId}")
    List<CommentDisplay> displayCommentForSchedule(@PathVariable Long scheduleId) {
        return commentService.findCommentsFromSchedule(scheduleId);
    }

    @PutMapping("/{id}")
    void editComment(@PathVariable Long id, @RequestBody @Valid CommentUpdateInput updateInput, BindingResult result) {
        FieldErrorFinder.isFieldHasError(result);
        commentService.editComment(id,updateInput);
    }

    @DeleteMapping("/{id}")
    void removeComment(@PathVariable Long id) {
        commentService.removeComment(id);
    }

}
