package com.example.reddit.repository;

import com.example.reddit.dto.Group;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupsRepository extends CrudRepository<Group, Long>  {
    Optional<Group> findByReferenceId(UUID referenceId);
}
