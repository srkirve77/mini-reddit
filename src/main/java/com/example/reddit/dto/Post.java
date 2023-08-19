package com.example.reddit.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PUBLIC)
@NoArgsConstructor
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    @JsonIgnore
    private Long id;
    UUID referenceId;
    UUID groupId;
    UUID userId;
    String content;
    List<UUID> usersLiked;
    List<UUID> commentIds;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    public Post(UUID referenceId, UUID groupId, UUID userId, String content, List<UUID> usersLiked,
                List<UUID> commentIds) {
        this.referenceId = referenceId;
        this.groupId = groupId;
        this.userId = userId;
        this.content = content;
        this.usersLiked = usersLiked;
        this.commentIds = commentIds;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
