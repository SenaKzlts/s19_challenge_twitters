package com.example.twitter_challenge.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(schema = "public", name = "likes")
public class Likes extends EntityBase{

    @NotNull(message = "Tweet cannot be null")
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "tweet_id")
    private Tweet tweet;

    @NotNull(message = "User cannot be null")
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "user_id")
    private User user;
}
