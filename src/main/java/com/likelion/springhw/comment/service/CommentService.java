package com.likelion.springhw.comment.service;

import com.likelion.springhw.comment.dto.CommentCreateRequest;
import com.likelion.springhw.comment.dto.CommentResponse;
import com.likelion.springhw.comment.dto.CommentUpdateRequest;
import com.likelion.springhw.comment.entity.Comment;
import com.likelion.springhw.comment.repository.CommentRepository;
import com.likelion.springhw.guestbook.entity.Guestbook;
import com.likelion.springhw.guestbook.repository.GuestbookRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final GuestbookRepository guestbookRepository;

    @Transactional
    public CommentResponse addComment(Long guestbookId, CommentCreateRequest request) {
        Guestbook guestbook = guestbookRepository.findById(guestbookId)
                .orElseThrow();

        Comment saved = commentRepository.save(new Comment(guestbook, request.getContent()));
        return new CommentResponse(saved);
    }

    public List<CommentResponse> getComments(Long guestbookId) {
        Guestbook guestbook = guestbookRepository.findById(guestbookId)
                .orElseThrow();
        List<CommentResponse> responses = new ArrayList<>();
        for (Comment comment : commentRepository.findAllByGuestbook(guestbook)) {
            responses.add(new CommentResponse(comment));
        }
        return responses;
    }

    @Transactional
    public CommentResponse updateComment(Long guestbookId, Long commentId,
                                         CommentUpdateRequest request) {
        Guestbook guestbook = guestbookRepository.findById(guestbookId)
                .orElseThrow();
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow();

        if (!comment.getGuestbook().getId().equals(guestbook.getId())) {
            return null;
        }

        // 조회한 엔티티의 변경은 트랜잭션이 끝날 때 변경 감지로 반영됩니다.
        comment.update(request.getContent());
        return new CommentResponse(comment);
    }

    @Transactional
    public void deleteComment(Long guestbookId, Long commentId) {
        Guestbook guestbook = guestbookRepository.findById(guestbookId)
                .orElseThrow();
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow();

        if (!comment.getGuestbook().getId().equals(guestbook.getId())) {
            return;
        }

        // 컬렉션에서 제거한 댓글은 orphanRemoval로 삭제합니다.
        comment.removeFromGuestbook();
    }

}
