package com.example.reddit;

import com.example.reddit.dto.Post;
import com.example.reddit.service.FeedsService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedsController {
    @Autowired
    public FeedsService feedsService;

    @GetMapping("/feed/{user_id}")
    public ResponseEntity<List<Post>> getFeedForUser(
        @PathVariable("user_id") UUID userId,
        @RequestParam(value = "group_id", defaultValue = "") String groupId,
        @RequestParam(value = "page_no", defaultValue = "0") Integer pageNo,
        @RequestParam(value = "page_size", defaultValue = "5") Integer pageSize
    ) {
        List<Post> posts = feedsService.getFeedPosts(userId, groupId, pageNo, pageSize);
        return ResponseEntity.ok(posts);
    }
}
