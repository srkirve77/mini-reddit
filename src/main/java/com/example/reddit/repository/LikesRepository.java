package com.example.reddit.repository;

import com.example.reddit.dto.Like;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikesRepository extends CrudRepository<Like, Long> {
    Optional<Like> findByUserIdAndPostId(UUID userId, UUID postId);
}
