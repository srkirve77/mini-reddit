package com.example.reddit.repository;

import com.example.reddit.dto.UserGroupMapping;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface UserGroupMappingRepository extends CrudRepository<UserGroupMapping, Long> {
    Optional<UserGroupMapping> findByUserIdAndGroupId(UUID userId, UUID groupId);
    List<UserGroupMapping> findByUserId(UUID userId);
}
