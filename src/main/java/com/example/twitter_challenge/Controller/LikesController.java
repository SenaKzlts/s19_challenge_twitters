package com.example.twitter_challenge.Controller;

import com.example.twitter_challenge.Entity.Likes;
import com.example.twitter_challenge.Service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/like")
public class LikesController {

    @Autowired
    private LikesService likesService;

    @GetMapping("/findByTweetId")
    public List<Likes> getLikesByTweetId(@RequestParam Long tweetId) {
        return likesService.getLikesByTweetId(tweetId);
    }

    @PostMapping
    public Likes createLike(@RequestBody Likes like) {
        return likesService.createLike(like);
    }
}
