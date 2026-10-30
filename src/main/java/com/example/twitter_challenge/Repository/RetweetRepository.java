package com.example.twitter_challenge.Repository;

import com.example.twitter_challenge.Entity.Retweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RetweetRepository extends JpaRepository<Retweet, Long> {
    Optional<Retweet> findByUserIdAndOriginalTweetId(Long userId, Long originalTweetId);
    List<Retweet> findByUserId(Long userId);
    List<Retweet> findByOriginalTweetId(Long originalTweetId);
}
