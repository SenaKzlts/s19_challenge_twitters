package com.example.twitter_challenge.Controller;

import com.example.twitter_challenge.Entity.Comments;
import com.example.twitter_challenge.Service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    @GetMapping("/findByTweetId")
    public List<Comments> getCommentsByTweetId(@RequestParam Long tweetId) {
        return commentsService.getCommentsByTweetId(tweetId);
    }

    @PostMapping
    public Comments createComment(@RequestBody Comments comment) {
        return commentsService.createComment(comment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comments> updateComment(@PathVariable Long id, @RequestBody Comments comment, @RequestParam Long userId) {
        try {
            Comments updatedComment = commentsService.updateComment(id, comment, userId);
            return ResponseEntity.ok(updatedComment);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id, @RequestParam Long userId) {
        try {
            commentsService.deleteComment(id, userId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
