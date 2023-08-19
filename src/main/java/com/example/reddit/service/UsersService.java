package com.example.reddit.service;

import com.example.reddit.dto.User;
import com.example.reddit.repository.UsersRepository;
import com.example.reddit.request.CreateUserRequest;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    public UsersRepository usersRepository;

    public User createUser(CreateUserRequest createUserRequest) {
        User user = new User(UUID.randomUUID(), createUserRequest.userName);
        return usersRepository.save(user);
    }

    public User getUser(UUID referenceId) {
        return usersRepository.findByReferenceId(referenceId).orElseThrow();
    }

    public void validateUser(UUID userId) {
        if (usersRepository.findByReferenceId(userId).isEmpty()) {
            throw new IllegalArgumentException("invalid user");
        }
    }
}
