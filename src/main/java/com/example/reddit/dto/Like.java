package com.example.reddit.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PUBLIC)
@NoArgsConstructor
@Table(name = "likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    @JsonIgnore
    private Long id;
    UUID referenceId;
    UUID userId;
    UUID postId;
    Boolean active;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    public Like(UUID referenceId, UUID userId, UUID postId, LocalDateTime createdAt,
                LocalDateTime updatedAt) {
        this.referenceId = referenceId;
        this.userId = userId;
        this.postId = postId;
        this.active = true;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
