package com.likelion.springhw.comment.controller;

import com.likelion.springhw.comment.dto.CommentCreateRequest;
import com.likelion.springhw.comment.dto.CommentResponse;
import com.likelion.springhw.comment.dto.CommentUpdateRequest;
import com.likelion.springhw.comment.service.CommentService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/guestbooks/{guestbookId}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponse create(@PathVariable Long guestbookId,
                                  @Valid @RequestBody CommentCreateRequest request) {
        return commentService.addComment(guestbookId, request);
    }

    @GetMapping
    public List<CommentResponse> list(@PathVariable Long guestbookId) {
        return commentService.getComments(guestbookId);
    }

    @PutMapping("/{commentId}")
    public CommentResponse update(@PathVariable Long guestbookId,
                                  @PathVariable Long commentId,
                                  @Valid @RequestBody CommentUpdateRequest request) {
        return commentService.updateComment(guestbookId, commentId, request);
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long guestbookId, @PathVariable Long commentId) {
        commentService.deleteComment(guestbookId, commentId);
    }
}
