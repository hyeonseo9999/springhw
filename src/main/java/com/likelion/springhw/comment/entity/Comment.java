package com.likelion.springhw.comment.entity;

import com.likelion.springhw.guestbook.entity.Guestbook;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "guestbook_comments")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String content;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guestbook_id", nullable = false)
    private Guestbook guestbook;

    public Comment(Guestbook guestbook, String content) {
        this.guestbook = guestbook;
        this.content = content;
        this.createdAt = LocalDateTime.now();
        guestbook.getComments().add(this);
    }

    public void update(String content) {
        this.content = content;
    }

    public void removeFromGuestbook() {
        guestbook.getComments().remove(this);
        this.guestbook = null;
    }
}
