package com.example.reddit.service;

import com.example.reddit.dto.Post;
import com.example.reddit.repository.PostsRepository;
import com.example.reddit.request.CreatePostRequest;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PostsService {

    @Autowired
    public PostsRepository postsRepository;
    @Autowired
    public UsersService usersService;
    @Autowired
    public GroupsService groupsService;

    public Post createPost(CreatePostRequest createPostRequest) {
        usersService.validateUser(createPostRequest.userId);
        groupsService.validateGroup(createPostRequest.groupId);

        Post post = new Post(UUID.randomUUID(), createPostRequest.groupId, createPostRequest.userId,
            createPostRequest.content, List.of(), List.of());
        return postsRepository.save(post);
    }

    public Post getPost(UUID postReferenceId) {
        return postsRepository.findByReferenceId(postReferenceId).orElseThrow();
    }

    public List<Post> getPostsCreatedByUser(UUID userReferenceId) {
        return postsRepository.findAllByUserId(userReferenceId);
    }

    public boolean toggleLike(UUID userReferenceId, UUID postReferenceId) {
        Post post = getPost(postReferenceId);
        boolean liked = false;
        if (post.usersLiked.contains(userReferenceId)) {
            post.usersLiked.remove(userReferenceId);
        } else {
            post.usersLiked.add(userReferenceId);
            liked = true;
        }
        postsRepository.save(post);
        return liked;
    }

    public void addComment(UUID postReferenceId, UUID commentReferenceId) {
        Post post = getPost(postReferenceId);
        post.commentIds.add(commentReferenceId);
        postsRepository.save(post);
    }

    public void validatePost(UUID referenceId) {
        if (postsRepository.findByReferenceId(referenceId).isEmpty()) {
            throw new IllegalArgumentException("invalid post");
        }
    }

    public List<Post> getLatestPostsByAllGroupId(List<UUID> groupIds, PageRequest pageRequest) {
        return postsRepository.findAllByGroupIdInOrderByCreatedAtDesc(groupIds, pageRequest);
    }
}
