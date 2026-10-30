package com.example.twitter_challenge.Controller;

import com.example.twitter_challenge.Service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dislike")
public class DislikeController {

    @Autowired
    private LikesService likesService;

    @PostMapping
    public ResponseEntity<Void> dislike(@RequestParam Long userId, @RequestParam Long tweetId) {
        likesService.deleteLikeByUserIdAndTweetId(userId, tweetId);
        return ResponseEntity.noContent().build();
    }
}
