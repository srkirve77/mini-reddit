package com.example.reddit.repository;

import com.example.reddit.dto.Post;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepository extends CrudRepository<Post, Long> {
    Optional<Post> findByReferenceId(UUID referenceId);
    List<Post> findAllByUserId(UUID userReferenceId);
    List<Post> findAllByGroupIdInOrderByCreatedAtDesc(List<UUID> groupId, Pageable pageable);
}
