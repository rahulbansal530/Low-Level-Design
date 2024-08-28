package com.intuit.marketplace.service.impl;

import com.intuit.marketplace.dto.request.UserRequest;
import com.intuit.marketplace.entity.User;
import com.intuit.marketplace.exception.UserAlreadyExistsException;
import com.intuit.marketplace.exception.UserNotFoundException;
import com.intuit.marketplace.mapper.UserDtoMapper;
import com.intuit.marketplace.repository.UserRepository;
import com.intuit.marketplace.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(UserRequest userRequest) {
        logger.info("Attempting to save user with email: {}", userRequest.getEmail());

        if (userRepository.existsByEmail(userRequest.getEmail())) {
            logger.error("User with email {} already exists", userRequest.getEmail());
            throw new UserAlreadyExistsException("User with email " + userRequest.getEmail() + " already exists",
                    HttpStatus.CONFLICT.name());
        }

        if (userRepository.existsByPhoneNumber(userRequest.getPhoneNumber())) {
            logger.error("User with phone number {} already exists", userRequest.getPhoneNumber());
            throw new UserAlreadyExistsException("User with phone number " + userRequest.getPhoneNumber() + " already" +
                    " exists",
                    HttpStatus.CONFLICT.name());
        }

        User user = UserDtoMapper.mapToUser(userRequest);

        User savedUser = userRepository.save(user);
        logger.info("User with email {} saved successfully with ID: {}", user.getEmail(), savedUser.getId());
        return savedUser;
    }

    @Override
    public User getUserByEmail(String email) {
        logger.info("Fetching user with email: {}", email);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    logger.error("User with email {} not found", email);
                    return new UserNotFoundException("User with email " + email + " not found", "USER_NOT_FOUND");
                });

        logger.info("User with email {} found with ID: {}", email, user.getId());
        return user;
    }

    @Override
    public User getUser(Long id) {
        logger.info("Fetching user with ID: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("User with ID {} not found", id);
                    return new UserNotFoundException("User with id " + id + " not found", "USER_NOT_FOUND");
                });

        logger.info("User with ID {} found with email: {}", id, user.getEmail());
        return user;
    }
}
