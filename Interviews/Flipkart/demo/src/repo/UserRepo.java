package Interviews.Flipkart.demo.src.repo;

import Interviews.Flipkart.demo.src.model.User;

public interface UserRepo {
    User getUserById(String id);

    void addUser(User user);
}
