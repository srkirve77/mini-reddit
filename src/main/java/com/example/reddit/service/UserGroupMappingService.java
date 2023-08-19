package com.example.reddit.service;

import com.example.reddit.dto.UserGroupMapping;
import com.example.reddit.repository.UserGroupMappingRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserGroupMappingService {

    @Autowired
    public UserGroupMappingRepository userGroupMappingRepository;

    public void createUserGroupMapping(UUID userId, UUID groupId) {
        Optional<UserGroupMapping> optionalUserGroupMapping =
            userGroupMappingRepository.findByUserIdAndGroupId(userId, groupId);

        if (optionalUserGroupMapping.isEmpty()) {
            UserGroupMapping userGroupMapping = new UserGroupMapping(userId, groupId);
            userGroupMappingRepository.save(userGroupMapping);
        }
    }

    public List<UserGroupMapping> getMappingsByUserId(UUID userId) {
        return userGroupMappingRepository.findByUserId(userId);
    }
}
