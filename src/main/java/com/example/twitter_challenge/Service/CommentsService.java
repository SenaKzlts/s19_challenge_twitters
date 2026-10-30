package com.example.twitter_challenge.Service;

import com.example.twitter_challenge.Entity.Comments;
import com.example.twitter_challenge.Repository.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentsService {

    @Autowired
    private CommentsRepository commentsRepository;

    public List<Comments> getAllComments() {
        return commentsRepository.findAll();
    }

    public List<Comments> getCommentsByTweetId(Long tweetId) {
        return commentsRepository.findByTweetId(tweetId);
    }

    public Optional<Comments> getCommentById(Long id) {
        return commentsRepository.findById(id);
    }

    public Comments createComment(Comments comment) {
        return commentsRepository.save(comment);
    }

    public Comments updateComment(Long id, Comments comment, Long currentUserId) {
        Comments existingComment = commentsRepository.findById(id).orElse(null);
        if (existingComment == null) {
            throw new RuntimeException("Comment not found");
        }
        if (!existingComment.getUser().getId().equals(currentUserId)) {
            throw new RuntimeException("You can only update your own comments");
        }
        comment.setId(id);
        return commentsRepository.save(comment);
    }

    public void deleteComment(Long id, Long currentUserId) {
        Comments existingComment = commentsRepository.findById(id).orElse(null);
        if (existingComment == null) {
            throw new RuntimeException("Comment not found");
        }
        if (!existingComment.getUser().getId().equals(currentUserId) &&
            !existingComment.getTweet().getUser().getId().equals(currentUserId)) {
            throw new RuntimeException("You can only delete your own comments or comments on your tweets");
        }
        commentsRepository.deleteById(id);
    }
}
