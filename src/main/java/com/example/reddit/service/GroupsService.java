package com.example.reddit.service;

import com.example.reddit.dto.Group;
import com.example.reddit.repository.GroupsRepository;
import com.example.reddit.request.CreateGroupRequest;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupsService {
    @Autowired
    public GroupsRepository groupsRepository;
    @Autowired
    public UserGroupMappingService userGroupMappingService;
    @Autowired
    public UsersService usersService;

    public Group createGroup(CreateGroupRequest createGroupRequest) {
        usersService.validateUser(createGroupRequest.userId);

        Group group = new Group(UUID.randomUUID(), createGroupRequest.userId, createGroupRequest.groupName);

        userGroupMappingService.createUserGroupMapping(createGroupRequest.userId, group.referenceId);
        return groupsRepository.save(group);
    }

    public void validateGroup(UUID groupId) {
        if (groupsRepository.findByReferenceId(groupId).isEmpty()) {
            throw new IllegalArgumentException("invalid group");
        }
    }
}
