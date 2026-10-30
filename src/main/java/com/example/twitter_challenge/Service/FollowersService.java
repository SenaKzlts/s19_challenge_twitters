package com.example.twitter_challenge.Service;

import com.example.twitter_challenge.Entity.Followers;
import com.example.twitter_challenge.Entity.User;
import com.example.twitter_challenge.Repository.FollowersRepository;
import com.example.twitter_challenge.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FollowersService {

    @Autowired
    private FollowersRepository followersRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Followers> getAllFollowers() {
        return followersRepository.findAll();
    }

    public Optional<Followers> getFollowerById(Long id) {
        return followersRepository.findById(id);
    }

    public Optional<Followers> findByFollowerIdAndFollowingId(Long followerId, Long followingId) {
        return followersRepository.findByFollowerIdAndFollowingId(followerId, followingId);
    }

    public Followers createFollower(Followers follower) {
        return followersRepository.save(follower);
    }

    public Followers followUser(Long followerId, Long followingId) {
        Optional<Followers> existing = followersRepository.findByFollowerIdAndFollowingId(followerId, followingId);
        if (existing.isPresent()) {
            throw new RuntimeException("Already following this user");
        }
        
        User followerUser = userRepository.findById(followerId)
                .orElseThrow(() -> new RuntimeException("Follower user not found"));
        User followingUser = userRepository.findById(followingId)
                .orElseThrow(() -> new RuntimeException("Following user not found"));
        
        Followers follower = new Followers();
        follower.setFollower(followerUser);
        follower.setFollowing(followingUser);
        return followersRepository.save(follower);
    }

    public void unfollowUser(Long followerId, Long followingId) {
        Optional<Followers> existing = followersRepository.findByFollowerIdAndFollowingId(followerId, followingId);
        existing.ifPresent(followersRepository::delete);
    }

    public Followers updateFollower(Long id, Followers follower) {
        if (followersRepository.existsById(id)) {
            follower.setId(id);
            return followersRepository.save(follower);
        }
        return null;
    }

    public void deleteFollower(Long id) {
        followersRepository.deleteById(id);
    }
}
