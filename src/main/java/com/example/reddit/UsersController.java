package com.example.reddit;


import com.example.reddit.dto.User;
import com.example.reddit.request.CreateUserRequest;
import com.example.reddit.service.UsersService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {
    @Autowired
    public UsersService usersService;

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") UUID referenceId) {
        User user = usersService.getUser(referenceId);
        return ResponseEntity.ok(user);
    }

    /**
     * @param createUserRequest contains username of the user to be created
     * @return newly created user
     */
    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequest createUserRequest) {
        User user = usersService.createUser(createUserRequest);
        return ResponseEntity.ok(user);
    }
}
