package Flipkart.UPISystem.src.repo.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import Interviews.Flipkart.demo.src.exceptions.CustomException;
import Interviews.Flipkart.demo.src.model.BankAccount;
import Interviews.Flipkart.demo.src.model.User;
import Interviews.Flipkart.demo.src.repo.UserRepo;
import hackathon.exceptions.NoUserFoundException;

public class UserRepoImpl implements UserRepo {
    Map<String, User> userIdToUser = new HashMap<>();

    public User getUserById(String id){
        User user = userIdToUser.get(id);
        if(Objects.isNull(id)) throw new NoUserFoundException("No user found with Id: " + id);
        return user;
    }

    public void addUser(User user) {
        if (checkUserExists(user)) {
            throw new CustomException("Duplicate Phone Number");
        }
        userIdToUser.put(user.getId(), user);
    }

    public User addBankAccount(String userId, BankAccount bankAccount) {
        userIdToUser.get(userId).addBankAccount(bankAccount);
        return userIdToUser.get(userId);
    }

    public boolean checkUserExists(User user) {
        for (User u : userIdToUser.values()) {
            if (u.getPhoneNumber().equals(user.getPhoneNumber())) {
                return true;
            }
        }
        return false;
    }
}
