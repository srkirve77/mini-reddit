package com.example.reddit.request;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PUBLIC)
public class CreateCommentRequest {
    UUID userId;
    UUID postId;
    String content;

    public CreateCommentRequest(UUID userId, UUID postId, String content) {
        this.userId = userId;
        this.postId = postId;
        this.content = content;
    }
}
