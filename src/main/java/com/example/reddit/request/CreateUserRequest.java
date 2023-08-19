package com.example.reddit.request;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PUBLIC)
public class CreateUserRequest {
    String userName;
}
