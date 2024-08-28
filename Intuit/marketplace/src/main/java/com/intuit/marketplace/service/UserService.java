package com.intuit.marketplace.service;

import com.intuit.marketplace.dto.request.UserRequest;
import com.intuit.marketplace.entity.User;

public interface UserService {

    User saveUser(UserRequest user);

    User getUserByEmail(String email);

    User getUser(Long id);
}
