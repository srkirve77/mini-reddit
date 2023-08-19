package com.example.reddit;

import com.example.reddit.dto.Post;
import com.example.reddit.request.CreatePostRequest;
import com.example.reddit.service.PostsService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostsController {

    @Autowired
    public PostsService postsService;

    /**
     * @param createPostRequest - groupId (group in which post is created), userId (user who created post), content (content of post)
     * @return returns newly created post
     */
    @PostMapping("/post")
    public ResponseEntity<Post> createPost(@RequestBody CreatePostRequest createPostRequest) {
        Post post = postsService.createPost(createPostRequest);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPost(@PathVariable("id") UUID referenceId) {
        Post post = postsService.getPost(referenceId);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/posts/user/{id}")
    public ResponseEntity<List<Post>> getPostsCreatedByUser(
        @PathVariable("id") UUID userReferenceId) {
        List<Post> posts = postsService.getPostsCreatedByUser(userReferenceId);
        return ResponseEntity.ok(posts);
    }
}
