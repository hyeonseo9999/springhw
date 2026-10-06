package com.likelion.springhw.comment.repository;

import com.likelion.springhw.comment.entity.Comment;
import com.likelion.springhw.guestbook.entity.Guestbook;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByGuestbook(Guestbook guestbook);
}
