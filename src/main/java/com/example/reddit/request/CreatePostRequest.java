package com.example.reddit.request;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PUBLIC)
public class CreatePostRequest {
    UUID groupId;
    UUID userId;
    String content;
}
