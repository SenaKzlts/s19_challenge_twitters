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
            user1.setUserName("twittergelistirici");
            user1.setEmail("dev@twitter.com");
            user1.setPassword(passwordEncoder.encode("password123"));
            user1.setBio("Resmi Twitter Geliştirici Hesabı");
            user1.setLocation(new Location(37.7749f, -122.4194f));
            user1 = userRepository.save(user1);

            User user2 = new User();
            user2.setUserName("teknolojihaberleri");
            user2.setEmail("news@tech.com");
            user2.setPassword(passwordEncoder.encode("password123"));
            user2.setBio("En son teknoloji haberleri ve güncellemeler");
            user2.setLocation(new Location(40.7128f, -74.0060f));
            user2 = userRepository.save(user2);

            User user3 = new User();
            user3.setUserName("kodhayati");
            user3.setEmail("code@life.com");
            user3.setPassword(passwordEncoder.encode("password123"));
            user3.setBio("Programlama ipuçları ve püf noktaları");
            user3.setLocation(new Location(30.2672f, -97.7431f));
            user3 = userRepository.save(user3);

            // Create sample tweets
            Tweet tweet1 = new Tweet();
            tweet1.setContent("Yeni Spring Boot uygulamamızı başarıyla yayına aldık! 🚀 #SpringBoot #Java");
            tweet1.setUser(user1);
            tweet1.setCreatedAt(new Date(System.currentTimeMillis() - 2 * 60 * 60 * 1000));
            tweetRepository.save(tweet1);

            Tweet tweet2 = new Tweet();
            tweet2.setContent("React 19 harika yeni özelliklerle geliyor! Denemek için sabırsızlanıyorum. ⚛️");
            tweet2.setUser(user2);
            tweet2.setCreatedAt(new Date(System.currentTimeMillis() - 5 * 60 * 60 * 1000));
            tweetRepository.save(tweet2);

            Tweet tweet3 = new Tweet();
            tweet3.setContent("İpucu: Her zaman anlamlı değişken isimleri kullanın. Gelecekteki kendiniz size teşekkür edecek! 💡 #Programlama #EnIyiUygulamalar");
            tweet3.setUser(user3);
            tweet3.setCreatedAt(new Date(System.currentTimeMillis() - 8 * 60 * 60 * 1000));
            tweetRepository.save(tweet3);

            Tweet tweet4 = new Tweet();
            tweet4.setContent("Docker deployment işini çok daha kolaylaştırıyor. Artık 'benim bilgisayarımda çalışıyor' sorunu yok! 🐳");
            tweet4.setUser(user1);
            tweet4.setCreatedAt(new Date(System.currentTimeMillis() - 12 * 60 * 60 * 1000));
            tweetRepository.save(tweet4);

            Tweet tweet5 = new Tweet();
            tweet5.setContent("TypeScript öğrenmek bu yıl verdiğim en iyi karardı. Tip güvenliği oyun değiştirici! 📝");
            tweet5.setUser(user3);
            tweet5.setCreatedAt(new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000));
            tweetRepository.save(tweet5);

            Tweet tweet6 = new Tweet();
            tweet6.setContent("Web geliştirmenin geleceği burada. En son AI destekli kodlama asistanlarını kontrol edin! 🤖");
            tweet6.setUser(user2);
            tweet6.setCreatedAt(new Date(System.currentTimeMillis() - 48 * 60 * 60 * 1000));
            tweetRepository.save(tweet6);

            System.out.println("Sample data initialized successfully!");
        }
    }
}
