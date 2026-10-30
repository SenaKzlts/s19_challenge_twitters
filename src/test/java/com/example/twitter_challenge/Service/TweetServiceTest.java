package com.example.twitter_challenge.Service;

import com.example.twitter_challenge.Entity.Tweet;
import com.example.twitter_challenge.Entity.User;
import com.example.twitter_challenge.Repository.TweetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TweetServiceTest {

    @Mock
    private TweetRepository tweetRepository;

    @InjectMocks
    private TweetService tweetService;

    private Tweet tweet;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setUserName("testuser");

        tweet = new Tweet();
        tweet.setId(1L);
        tweet.setContent("Test tweet content");
        tweet.setUser(user);
    }

    @Test
    void getAllTweets_ShouldReturnAllTweets() {
        List<Tweet> tweets = Arrays.asList(tweet);
        when(tweetRepository.findAll()).thenReturn(tweets);

        List<Tweet> result = tweetService.getAllTweets();

        assertEquals(1, result.size());
        assertEquals("Test tweet content", result.get(0).getContent());
        verify(tweetRepository, times(1)).findAll();
    }

    @Test
    void getTweetById_WhenTweetExists_ShouldReturnTweet() {
        when(tweetRepository.findById(1L)).thenReturn(Optional.of(tweet));

        Optional<Tweet> result = tweetService.getTweetById(1L);

        assertTrue(result.isPresent());
        assertEquals("Test tweet content", result.get().getContent());
        verify(tweetRepository, times(1)).findById(1L);
    }

    @Test
    void getTweetById_WhenTweetNotExists_ShouldReturnEmpty() {
        when(tweetRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Tweet> result = tweetService.getTweetById(1L);

        assertFalse(result.isPresent());
        verify(tweetRepository, times(1)).findById(1L);
    }

    @Test
    void createTweet_ShouldReturnSavedTweet() {
        when(tweetRepository.save(any(Tweet.class))).thenReturn(tweet);

        Tweet result = tweetService.createTweet(tweet);

        assertNotNull(result);
        assertEquals("Test tweet content", result.getContent());
        verify(tweetRepository, times(1)).save(tweet);
    }

    @Test
    void getTweetsByUserId_ShouldReturnUserTweets() {
        List<Tweet> tweets = Arrays.asList(tweet);
        when(tweetRepository.findByUserId(1L)).thenReturn(tweets);

        List<Tweet> result = tweetService.getTweetsByUserId(1L);

        assertEquals(1, result.size());
        assertEquals("Test tweet content", result.get(0).getContent());
        verify(tweetRepository, times(1)).findByUserId(1L);
    }

    @Test
    void updateTweet_WhenTweetExistsAndUserIsOwner_ShouldUpdateTweet() {
        when(tweetRepository.findById(1L)).thenReturn(Optional.of(tweet));
        when(tweetRepository.save(any(Tweet.class))).thenReturn(tweet);

        Tweet result = tweetService.updateTweet(1L, tweet, 1L);

        assertNotNull(result);
        verify(tweetRepository, times(1)).save(tweet);
    }

    @Test
    void updateTweet_WhenUserIsNotOwner_ShouldThrowException() {
        User otherUser = new User();
        otherUser.setId(2L);
        tweet.setUser(otherUser);

        when(tweetRepository.findById(1L)).thenReturn(Optional.of(tweet));

        assertThrows(RuntimeException.class, () -> tweetService.updateTweet(1L, tweet, 1L));
    }

    @Test
    void deleteTweet_WhenTweetExistsAndUserIsOwner_ShouldDeleteTweet() {
        when(tweetRepository.findById(1L)).thenReturn(Optional.of(tweet));
        doNothing().when(tweetRepository).deleteById(1L);

        tweetService.deleteTweet(1L, 1L);

        verify(tweetRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteTweet_WhenUserIsNotOwner_ShouldThrowException() {
        User otherUser = new User();
        otherUser.setId(2L);
        tweet.setUser(otherUser);

        when(tweetRepository.findById(1L)).thenReturn(Optional.of(tweet));

        assertThrows(RuntimeException.class, () -> tweetService.deleteTweet(1L, 1L));
    }
}
