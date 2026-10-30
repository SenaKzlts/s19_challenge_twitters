package com.example.twitter_challenge.Controller;

import com.example.twitter_challenge.Entity.Tweet;
import com.example.twitter_challenge.Service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tweet")
public class TweetController {

    @Autowired
    private TweetService tweetService;

    @GetMapping
    public List<Tweet> getAllTweets() {
        return tweetService.getAllTweets();
    }

    @PostMapping
    public Tweet createTweet(@RequestBody Tweet tweet) {
        return tweetService.createTweet(tweet);
    }

    @GetMapping("/findByUserId")
    public List<Tweet> getTweetsByUserId(@RequestParam Long userId) {
        return tweetService.getTweetsByUserId(userId);
    }

    @GetMapping("/findById")
    public ResponseEntity<Tweet> getTweetById(@RequestParam Long id) {
        return tweetService.getTweetById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tweet> updateTweet(@PathVariable Long id, @RequestBody Tweet tweet, @RequestParam Long userId) {
        try {
            Tweet updatedTweet = tweetService.updateTweet(id, tweet, userId);
            return ResponseEntity.ok(updatedTweet);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTweet(@PathVariable Long id, @RequestParam Long userId) {
        try {
            tweetService.deleteTweet(id, userId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
