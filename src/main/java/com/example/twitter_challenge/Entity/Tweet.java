package com.example.twitter_challenge.Entity;


import com.example.twitter_challenge.Utils.Commons.Location;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(schema = "public", name = "tweets")
public class Tweet extends EntityBase{

    @NotBlank(message = "Tweet content cannot be blank")
    @Size(max = 280, message = "Tweet content cannot exceed 280 characters")
    private String content;

//    //todo : teknik borc-> user classini bagla
//    private Long user_id;

    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST},
            fetch =FetchType.LAZY
    )
    @JoinColumn(name = "user_id")
    private User user;

    @Embedded
    private Location location;

    //retweet
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "parent_tweet_id")
    private Tweet parentTweet;

    //retweets
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "originalTweet")
    private java.util.List<Retweet> retweets;

    //comments
    //likes

}
