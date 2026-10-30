package com.example.twitter_challenge.Config;

import com.example.twitter_challenge.Entity.Tweet;
import com.example.twitter_challenge.Entity.User;
import com.example.twitter_challenge.Repository.TweetRepository;
import com.example.twitter_challenge.Repository.UserRepository;
import com.example.twitter_challenge.Utils.Commons.Location;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final TweetRepository tweetRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, TweetRepository tweetRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.tweetRepository = tweetRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // Create sample users if they don't exist
        if (userRepository.count() == 0) {
            User user1 = new User();
            user1.setUserName("twitterdev");
            user1.setEmail("dev@twitter.com");
            user1.setPassword(passwordEncoder.encode("password123"));
            user1.setBio("Official Twitter Developer account");
            user1.setLocation(new Location(37.7749f, -122.4194f));
            user1 = userRepository.save(user1);

            User user2 = new User();
            user2.setUserName("technews");
            user2.setEmail("news@tech.com");
            user2.setPassword(passwordEncoder.encode("password123"));
            user2.setBio("Latest technology news and updates");
            user2.setLocation(new Location(40.7128f, -74.0060f));
            user2 = userRepository.save(user2);

            User user3 = new User();
            user3.setUserName("codinglife");
            user3.setEmail("code@life.com");
            user3.setPassword(passwordEncoder.encode("password123"));
            user3.setBio("Programming tips and tricks");
            user3.setLocation(new Location(30.2672f, -97.7431f));
            user3 = userRepository.save(user3);

            // Create sample tweets
            Tweet tweet1 = new Tweet();
            tweet1.setContent("Just deployed our new Spring Boot application! 🚀 #SpringBoot #Java");
            tweet1.setUser(user1);
            tweet1.setCreatedAt(new Date(System.currentTimeMillis() - 2 * 60 * 60 * 1000));
            tweetRepository.save(tweet1);

            Tweet tweet2 = new Tweet();
            tweet2.setContent("React 19 is coming with amazing new features! Can't wait to try them out. ⚛️");
            tweet2.setUser(user2);
            tweet2.setCreatedAt(new Date(System.currentTimeMillis() - 5 * 60 * 60 * 1000));
            tweetRepository.save(tweet2);

            Tweet tweet3 = new Tweet();
            tweet3.setContent("Pro tip: Always use meaningful variable names. Future you will thank present you! 💡 #Programming #BestPractices");
            tweet3.setUser(user3);
            tweet3.setCreatedAt(new Date(System.currentTimeMillis() - 8 * 60 * 60 * 1000));
            tweetRepository.save(tweet3);

            Tweet tweet4 = new Tweet();
            tweet4.setContent("Docker makes deployment so much easier. No more 'it works on my machine' problems! 🐳");
            tweet4.setUser(user1);
            tweet4.setCreatedAt(new Date(System.currentTimeMillis() - 12 * 60 * 60 * 1000));
            tweetRepository.save(tweet4);

            Tweet tweet5 = new Tweet();
            tweet5.setContent("Learning TypeScript was the best decision I made this year. Type safety is a game changer! 📝");
            tweet5.setUser(user3);
            tweet5.setCreatedAt(new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000));
            tweetRepository.save(tweet5);

            Tweet tweet6 = new Tweet();
            tweet6.setContent("The future of web development is here. Check out the latest AI-powered coding assistants! 🤖");
            tweet6.setUser(user2);
            tweet6.setCreatedAt(new Date(System.currentTimeMillis() - 48 * 60 * 60 * 1000));
            tweetRepository.save(tweet6);

            System.out.println("Sample data initialized successfully!");
        }
    }
}
