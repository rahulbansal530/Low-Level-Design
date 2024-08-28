package com.intuit.marketplace.service.impl;

import com.intuit.marketplace.dto.request.UserRequest;
import com.intuit.marketplace.entity.User;
import com.intuit.marketplace.exception.UserAlreadyExistsException;
import com.intuit.marketplace.exception.UserNotFoundException;
import com.intuit.marketplace.mapper.UserDtoMapper;
import com.intuit.marketplace.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveUser_ShouldThrowException_WhenEmailAlreadyExists() {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("existing@example.com");
        userRequest.setPhoneNumber("1234567890");

        when(userRepository.existsByEmail(userRequest.getEmail())).thenReturn(true);

        UserAlreadyExistsException exception = assertThrows(UserAlreadyExistsException.class, () -> {
            userService.saveUser(userRequest);
        });

        assertEquals("User with email existing@example.com already exists", exception.getMessage());
        assertEquals(HttpStatus.CONFLICT.name(), exception.getErrorCode());
    }

    @Test
    void saveUser_ShouldThrowException_WhenPhoneNumberAlreadyExists() {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("new@example.com");
        userRequest.setPhoneNumber("1234567890");

        when(userRepository.existsByEmail(userRequest.getEmail())).thenReturn(false);
        when(userRepository.existsByPhoneNumber(userRequest.getPhoneNumber())).thenReturn(true);

        UserAlreadyExistsException exception = assertThrows(UserAlreadyExistsException.class, () -> {
            userService.saveUser(userRequest);
        });

        assertEquals("User with phone number 1234567890 already exists", exception.getMessage());
        assertEquals(HttpStatus.CONFLICT.name(), exception.getErrorCode());
    }

    @Test
    void saveUser_ShouldSaveUser_WhenNotExists() {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("new@example.com");
        userRequest.setPhoneNumber("1234567890");

        when(userRepository.existsByEmail(userRequest.getEmail())).thenReturn(false);
        when(userRepository.existsByPhoneNumber(userRequest.getPhoneNumber())).thenReturn(false);

        mockStatic(UserDtoMapper.class);
        User user = new User();
        when(UserDtoMapper.mapToUser(userRequest)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);

        User savedUser = userService.saveUser(userRequest);

        assertNotNull(savedUser);
        assertEquals(user, savedUser);
    }

    @Test
    void getUserByEmail_ShouldReturnUser_WhenExists() {
        String email = "existing@example.com";
        User user = new User();
        user.setEmail(email);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        User result = userService.getUserByEmail(email);

        assertNotNull(result);
        assertEquals(email, result.getEmail());
    }

    @Test
    void getUserByEmail_ShouldThrowException_WhenNotExists() {
        String email = "nonexistent@example.com";

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            userService.getUserByEmail(email);
        });

        assertEquals("User with email nonexistent@example.com not found", exception.getMessage());
        assertEquals("USER_NOT_FOUND", exception.getErrorCode());
    }

    @Test
    void getUser_ShouldReturnUser_WhenExists() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User result = userService.getUser(userId);

        assertNotNull(result);
        assertEquals(userId, result.getId());
    }

    @Test
    void getUser_ShouldThrowException_WhenNotExists() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            userService.getUser(userId);
        });

        assertEquals("User with id 1 not found", exception.getMessage());
        assertEquals("USER_NOT_FOUND", exception.getErrorCode());
    }
}
