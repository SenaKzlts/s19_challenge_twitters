package com.example.twitter_challenge.Repository;

import com.example.twitter_challenge.Entity.Followers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FollowersRepository extends JpaRepository<Followers, Long> {
    Optional<Followers> findByFollowerIdAndFollowingId(Long followerId, Long followingId);
}
