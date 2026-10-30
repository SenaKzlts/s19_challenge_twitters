package com.example.twitter_challenge.Repository;

import com.example.twitter_challenge.Entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {
    List<Comments> findByTweetId(Long tweetId);
}
