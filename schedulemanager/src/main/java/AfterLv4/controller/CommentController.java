package AfterLv4.controller;

import AfterLv4.domain.Comment;
import AfterLv4.dto.comment.CommentDisplay;
import AfterLv4.dto.comment.CommentInput;
import AfterLv4.dto.comment.CommentUpdateInput;
import AfterLv4.service.CommentService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
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
    void addComment(@RequestBody @Valid CommentInput commentInput) {
        commentService.addComment(commentInput);
    }

    @GetMapping("/{scheduleId}")
    List<CommentDisplay> displayCommentForSchedule(@PathVariable Long scheduleId) {
        return commentService.findCommentsFromSchedule(scheduleId);
    }

    @PutMapping("/{id}")
    void editComment(@PathVariable Long id, @RequestBody @Valid CommentUpdateInput updateInput) {
        commentService.editComment(id,updateInput);
    }

    @DeleteMapping("/{id}")
    void removeComment(@PathVariable Long id) {
        commentService.removeComment(id);
    }
}
