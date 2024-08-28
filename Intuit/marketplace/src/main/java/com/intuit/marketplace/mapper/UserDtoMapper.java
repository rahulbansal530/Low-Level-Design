package com.intuit.marketplace.mapper;

import com.intuit.marketplace.dto.request.UserRequest;
import com.intuit.marketplace.entity.User;

public class UserDtoMapper {
    public static User mapToUser(UserRequest userRequest) {

        return User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail())
                .phoneNumber(userRequest.getPhoneNumber())
                .build();
    }
}
