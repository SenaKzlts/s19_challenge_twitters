package com.example.twitter_challenge.Service;

import com.example.twitter_challenge.Entity.Retweet;
import com.example.twitter_challenge.Repository.RetweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RetweetService {

    @Autowired
    private RetweetRepository retweetRepository;

    public List<Retweet> getAllRetweets() {
        return retweetRepository.findAll();
    }

    public Optional<Retweet> getRetweetById(Long id) {
        return retweetRepository.findById(id);
    }

    public Retweet createRetweet(Retweet retweet) {
        return retweetRepository.save(retweet);
    }

    public void deleteRetweet(Long id) {
        retweetRepository.deleteById(id);
    }

    public Optional<Retweet> findByUserIdAndOriginalTweetId(Long userId, Long originalTweetId) {
        return retweetRepository.findByUserIdAndOriginalTweetId(userId, originalTweetId);
    }
}
