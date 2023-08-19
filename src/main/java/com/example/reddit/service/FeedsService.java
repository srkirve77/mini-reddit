package com.example.reddit.service;

import com.example.reddit.dto.Post;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class FeedsService {
    @Autowired
    public GroupsService groupsService;
    @Autowired
    public UserGroupMappingService userGroupMappingService;
    @Autowired
    public PostsService postsService;


    /**
     * @param userId
     * @param groupId
     * @param pageNo
     * @param pageSize if user wants posts from specific group then return posts from that group only
     *                 else return few posts from all other groups
     * @return
     */
    public List<Post> getFeedPosts(UUID userId, String groupId, Integer pageNo, Integer pageSize) {
        if (!Objects.equals("", groupId)) {
            return postsService.getLatestPostsByAllGroupId(List.of(UUID.fromString(groupId)),
                PageRequest.of(pageNo, pageSize));
        }

        List<UUID> groupIds =
            userGroupMappingService.getMappingsByUserId(userId).stream().map(group -> group.groupId)
                .toList();

        return postsService.getLatestPostsByAllGroupId(groupIds, PageRequest.of(pageNo, pageSize));
    }
}
