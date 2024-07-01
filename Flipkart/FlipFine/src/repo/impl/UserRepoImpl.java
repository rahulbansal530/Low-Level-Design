package repo.impl;

import exceptions.NoUserFoundException;
import model.User;
import repo.UserRepo;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UserRepoImpl implements UserRepo {
    Map<String, User> userIdToUser = new HashMap<>();

    public User getUserById(String id) {
        User user = userIdToUser.get(id);
        if (Objects.isNull(id)) throw new NoUserFoundException("No user found with Id: " + id);
        return user;
    }


}
