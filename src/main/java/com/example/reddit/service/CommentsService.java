package com.example.reddit.service;

import com.example.reddit.dto.Comment;
import com.example.reddit.repository.CommentsRepository;
import com.example.reddit.request.CreateCommentRequest;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentsService {
    @Autowired
    public CommentsRepository commentsRepository;
    @Autowired
    public PostsService postsService;
    @Autowired
    public UsersService usersService;

    /**
     *
     * @param createCommentRequest
     * @return newly created comment
     */
    public Comment postComment(CreateCommentRequest createCommentRequest) {
        postsService.validatePost(createCommentRequest.postId);
        usersService.validateUser(createCommentRequest.userId);

        Comment comment = new Comment(UUID.randomUUID(), createCommentRequest.userId, createCommentRequest.postId,
            createCommentRequest.content, LocalDateTime.now(), LocalDateTime.now());

        Comment savedComment = commentsRepository.save(comment);

        postsService.addComment(savedComment.postId, savedComment.referenceId);

        return savedComment;
    }
}
