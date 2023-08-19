package com.example.reddit.service;

import static org.mockito.ArgumentMatchers.any;

import com.example.reddit.dto.Comment;
import com.example.reddit.repository.CommentsRepository;
import com.example.reddit.request.CreateCommentRequest;
import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CommentsServiceTest {
    @Mock
    CommentsRepository commentsRepository;
    @Mock
    PostsService postsService;
    @Mock
    UsersService usersService;
    @InjectMocks
    CommentsService commentsService;

    @Test
    public void testPostComment() {
        //mock
        UUID userId = UUID.randomUUID();
        UUID postId = UUID.randomUUID();
        CreateCommentRequest commentRequest = new CreateCommentRequest(userId, postId, "content");
        Comment comment = new Comment(UUID.randomUUID(), userId, postId,
            "content", LocalDateTime.now(), LocalDateTime.now());
        Mockito.when(commentsRepository.save(any())).thenReturn(comment);
        Mockito.doNothing().when(postsService).addComment(any(), any());

        //act
        Comment result = commentsService.postComment(commentRequest);

        //verify
        Assertions.assertEquals(userId, result.userId);
        Assertions.assertEquals(postId, result.postId);
        Assertions.assertEquals("content", result.content);
        Assertions.assertNotNull(result.referenceId);
    }
}