package com.example.twitter_challenge.Service;

import com.example.twitter_challenge.Entity.Likes;
import com.example.twitter_challenge.Repository.LikesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikesService {

    @Autowired
    private LikesRepository likesRepository;

    public List<Likes> getAllLikes() {
        return likesRepository.findAll();
    }

    public List<Likes> getLikesByTweetId(Long tweetId) {
        return likesRepository.findByTweetId(tweetId);
    }

    public Optional<Likes> getLikeById(Long id) {
        return likesRepository.findById(id);
    }

    public Likes createLike(Likes like) {
        return likesRepository.save(like);
    }

    public void deleteLikeByUserIdAndTweetId(Long userId, Long tweetId) {
        likesRepository.findByUserIdAndTweetId(userId, tweetId).ifPresent(likesRepository::delete);
    }

    public Likes updateLike(Long id, Likes like) {
        if (likesRepository.existsById(id)) {
            like.setId(id);
            return likesRepository.save(like);
        }
        return null;
    }

    public void deleteLike(Long id) {
        likesRepository.deleteById(id);
    }
}
