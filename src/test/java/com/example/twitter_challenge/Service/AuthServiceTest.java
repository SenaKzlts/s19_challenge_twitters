package com.example.twitter_challenge.Service;

import com.example.twitter_challenge.DTO.LoginRequest;
import com.example.twitter_challenge.DTO.RegisterRequest;
import com.example.twitter_challenge.Entity.User;
import com.example.twitter_challenge.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;

    private RegisterRequest registerRequest;
    private LoginRequest loginRequest;
    private User user;

    @BeforeEach
    void setUp() {
        registerRequest = new RegisterRequest();
        registerRequest.setUserName("testuser");
        registerRequest.setEmail("test@example.com");
        registerRequest.setPassword("password123");

        loginRequest = new LoginRequest();
        loginRequest.setEmail("test@example.com");
        loginRequest.setPassword("password123");

        user = new User();
        user.setId(1L);
        user.setUserName("testuser");
        user.setEmail("test@example.com");
        user.setPassword("encodedPassword");
    }

    @Test
    void register_WhenEmailNotExists_ShouldReturnUser() {
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());
        when(userRepository.findByUserName("testuser")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = authService.register(registerRequest);

        assertNotNull(result);
        assertEquals("testuser", result.getUserName());
        assertEquals("test@example.com", result.getEmail());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void register_WhenEmailExists_ShouldThrowException() {
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        assertThrows(RuntimeException.class, () -> authService.register(registerRequest));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void register_WhenUsernameExists_ShouldThrowException() {
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());
        when(userRepository.findByUserName("testuser")).thenReturn(Optional.of(user));

        assertThrows(RuntimeException.class, () -> authService.register(registerRequest));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void login_WhenCredentialsValid_ShouldReturnToken() {
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("password123", "encodedPassword")).thenReturn(true);

        String result = authService.login(loginRequest);

        assertNotNull(result);
        assertEquals("jwt_token_placeholder", result);
    }

    @Test
    void login_WhenUserNotFound_ShouldThrowException() {
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> authService.login(loginRequest));
    }

    @Test
    void login_WhenPasswordInvalid_ShouldThrowException() {
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("password123", "encodedPassword")).thenReturn(false);

        assertThrows(RuntimeException.class, () -> authService.login(loginRequest));
    }
}
