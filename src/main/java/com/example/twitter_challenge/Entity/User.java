package com.example.twitter_challenge.Entity;


import com.example.twitter_challenge.Utils.Commons.Location;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(schema = "public", name = "users")
public class User extends EntityBase{

    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String userName;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    private Date birthday;
    @Embedded
    private Location location;
    @Size(max = 160, message = "Bio cannot exceed 160 characters")
    private String bio;
    private String photo;
    private String banner;

    //tweets
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Tweet> tweets;

}
