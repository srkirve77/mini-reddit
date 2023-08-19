package com.example.reddit.repository;

import com.example.reddit.dto.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<User, Long> {
    Optional<User> findByReferenceId(UUID referenceId);
}
