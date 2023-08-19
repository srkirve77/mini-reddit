package com.example.reddit;

import com.example.reddit.dto.Group;
import com.example.reddit.request.CreateGroupRequest;
import com.example.reddit.service.GroupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroupsController {

    @Autowired
    public GroupsService groupsService;

    @PostMapping("/group")
    public ResponseEntity<?> createGroup(@RequestBody CreateGroupRequest createGroupRequest) {
        Group group = groupsService.createGroup(createGroupRequest);
        return ResponseEntity.ok(group);
    }
}
