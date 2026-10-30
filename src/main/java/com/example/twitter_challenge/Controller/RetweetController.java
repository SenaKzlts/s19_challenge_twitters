package com.example.twitter_challenge.Controller;

import com.example.twitter_challenge.Entity.Retweet;
import com.example.twitter_challenge.Service.RetweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/retweet")
public class RetweetController {

    @Autowired
    private RetweetService retweetService;

    @PostMapping
    public Retweet createRetweet(@RequestBody Retweet retweet) {
        return retweetService.createRetweet(retweet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRetweet(@PathVariable Long id) {
        retweetService.deleteRetweet(id);
        return ResponseEntity.noContent().build();
    }
}
