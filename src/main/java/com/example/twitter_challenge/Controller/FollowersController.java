package com.example.twitter_challenge.Controller;

import com.example.twitter_challenge.Entity.Followers;
import com.example.twitter_challenge.Service.FollowersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/followers")
public class FollowersController {

    @Autowired
    private FollowersService followersService;

    @GetMapping
    public List<Followers> getAllFollowers() {
        return followersService.getAllFollowers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Followers> getFollowerById(@PathVariable Long id) {
        return followersService.getFollowerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/follow")
    public ResponseEntity<Followers> followUser(@RequestParam Long followerId, @RequestParam Long followingId) {
        try {
            Followers follower = followersService.followUser(followerId, followingId);
            return ResponseEntity.ok(follower);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/unfollow")
    public ResponseEntity<Void> unfollowUser(@RequestParam Long followerId, @RequestParam Long followingId) {
        followersService.unfollowUser(followerId, followingId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public Followers createFollower(@RequestBody Followers follower) {
        return followersService.createFollower(follower);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Followers> updateFollower(@PathVariable Long id, @RequestBody Followers follower) {
        Followers updatedFollower = followersService.updateFollower(id, follower);
        if (updatedFollower != null) {
            return ResponseEntity.ok(updatedFollower);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFollower(@PathVariable Long id) {
        followersService.deleteFollower(id);
        return ResponseEntity.noContent().build();
    }
}
