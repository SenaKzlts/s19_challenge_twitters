package com.example.twitter_challenge.Service;

import com.example.twitter_challenge.Entity.Tweet;
import com.example.twitter_challenge.Repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TweetService {

    @Autowired
    private TweetRepository tweetRepository;

    public List<Tweet> getAllTweets() {
        return tweetRepository.findAll();
    }

    public List<Tweet> getTweetsByUserId(Long userId) {
        return tweetRepository.findByUserId(userId);
    }

    public Optional<Tweet> getTweetById(Long id) {
        return tweetRepository.findById(id);
    }

    public Tweet createTweet(Tweet tweet) {
        return tweetRepository.save(tweet);
    }

    public Tweet updateTweet(Long id, Tweet tweet, Long currentUserId) {
        Tweet existingTweet = tweetRepository.findById(id).orElse(null);
        if (existingTweet == null) {
            throw new RuntimeException("Tweet not found");
        }
        if (!existingTweet.getUser().getId().equals(currentUserId)) {
            throw new RuntimeException("You can only update your own tweets");
        }
        tweet.setId(id);
        return tweetRepository.save(tweet);
    }

    public void deleteTweet(Long id, Long currentUserId) {
        Tweet existingTweet = tweetRepository.findById(id).orElse(null);
        if (existingTweet == null) {
            throw new RuntimeException("Tweet not found");
        }
        if (!existingTweet.getUser().getId().equals(currentUserId)) {
            throw new RuntimeException("You can only delete your own tweets");
        }
        tweetRepository.deleteById(id);
    }
}
