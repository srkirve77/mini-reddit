package com.example.reddit.service;

import com.example.reddit.dto.Like;
import com.example.reddit.repository.LikesRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikesService {

    @Autowired
    public LikesRepository likesRepository;
    @Autowired
    public PostsService postsService;
    @Autowired
    public UsersService usersService;

    /**
     *
     * @param userReferenceId
     * @param postReferenceId
     * if user has already liked a comment then dislikes that comment
     * @return true if user has liked or false if user has disliked
     */
    public boolean toggleLike(UUID userReferenceId, UUID postReferenceId) {
        usersService.validateUser(userReferenceId);
        postsService.validatePost(postReferenceId);

        Optional<Like> optionalLike = likesRepository.findByUserIdAndPostId(userReferenceId, postReferenceId);

        if (optionalLike.isPresent()) {
            optionalLike.get().active = !optionalLike.get().active;
            optionalLike.get().updatedAt = LocalDateTime.now();
            likesRepository.save(optionalLike.get());
        } else {
            Like like = new Like(UUID.randomUUID(), userReferenceId, postReferenceId, LocalDateTime.now(), LocalDateTime.now());
            likesRepository.save(like);
        }

        return postsService.toggleLike(userReferenceId, postReferenceId);
    }
}
