package com.example.reddit;

import com.example.reddit.dto.Comment;
import com.example.reddit.request.CreateCommentRequest;
import com.example.reddit.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentsController {
    @Autowired
    public CommentsService commentsService;

    @PostMapping("/comment")
    public ResponseEntity<?> createUser(
        @RequestBody CreateCommentRequest createCommentRequest
    ) {
        Comment comment = commentsService.postComment(createCommentRequest);
        return ResponseEntity.ok(comment);
    }

}
