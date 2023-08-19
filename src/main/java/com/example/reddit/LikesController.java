package com.example.reddit;

import com.example.reddit.service.LikesService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikesController {
    @Autowired
    public LikesService likesService;

    @PutMapping("/toggle-like")
    public ResponseEntity<String> toggleLike(@RequestParam UUID userReferenceId,
                                             @RequestParam UUID postReferenceId) {
        boolean liked = likesService.toggleLike(userReferenceId, postReferenceId);
        return ResponseEntity.ok(liked ? "liked :)" : "unliked ;)");
    }
}
