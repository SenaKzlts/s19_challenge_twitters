package com.example.twitter_challenge.Service;

import com.example.twitter_challenge.Entity.Comments;
import com.example.twitter_challenge.Entity.Tweet;
import com.example.twitter_challenge.Entity.User;
import com.example.twitter_challenge.Repository.CommentsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommentsServiceTest {

    @Mock
    private CommentsRepository commentsRepository;

    @InjectMocks
    private CommentsService commentsService;

    private Comments comment;
    private User user;
    private Tweet tweet;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setUserName("testuser");

        tweet = new Tweet();
        tweet.setId(1L);
        tweet.setContent("Test tweet");
        tweet.setUser(user);

        comment = new Comments();
        comment.setId(1L);
        comment.setCommentText("Test comment");
        comment.setUser(user);
        comment.setTweet(tweet);
    }

    @Test
    void createComment_ShouldReturnSavedComment() {
        when(commentsRepository.save(any(Comments.class))).thenReturn(comment);

        Comments result = commentsService.createComment(comment);

        assertNotNull(result);
        assertEquals("Test comment", result.getCommentText());
        verify(commentsRepository, times(1)).save(comment);
    }

    @Test
    void updateComment_WhenCommentExistsAndUserIsOwner_ShouldUpdateComment() {
        when(commentsRepository.findById(1L)).thenReturn(Optional.of(comment));
        when(commentsRepository.save(any(Comments.class))).thenReturn(comment);

        Comments result = commentsService.updateComment(1L, comment, 1L);

        assertNotNull(result);
        verify(commentsRepository, times(1)).save(comment);
    }

    @Test
    void updateComment_WhenUserIsNotOwner_ShouldThrowException() {
        User otherUser = new User();
        otherUser.setId(2L);
        comment.setUser(otherUser);

        when(commentsRepository.findById(1L)).thenReturn(Optional.of(comment));

        assertThrows(RuntimeException.class, () -> commentsService.updateComment(1L, comment, 1L));
    }

    @Test
    void deleteComment_WhenCommentExistsAndUserIsOwner_ShouldDeleteComment() {
        when(commentsRepository.findById(1L)).thenReturn(Optional.of(comment));
        doNothing().when(commentsRepository).deleteById(1L);

        commentsService.deleteComment(1L, 1L);

        verify(commentsRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteComment_WhenUserIsTweetOwner_ShouldDeleteComment() {
        User commentUser = new User();
        commentUser.setId(2L);
        comment.setUser(commentUser);

        when(commentsRepository.findById(1L)).thenReturn(Optional.of(comment));
        doNothing().when(commentsRepository).deleteById(1L);

        commentsService.deleteComment(1L, 1L);

        verify(commentsRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteComment_WhenUserIsNotOwnerOrTweetOwner_ShouldThrowException() {
        User commentUser = new User();
        commentUser.setId(2L);
        User tweetUser = new User();
        tweetUser.setId(3L);
        comment.setUser(commentUser);
        tweet.setUser(tweetUser);

        when(commentsRepository.findById(1L)).thenReturn(Optional.of(comment));

        assertThrows(RuntimeException.class, () -> commentsService.deleteComment(1L, 1L));
    }
}
