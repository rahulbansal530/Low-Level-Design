package Flipkart.UPISystem.src.service;

import Interviews.Flipkart.demo.src.model.User;


public interface UserService {
    User addUser(String name, String phoneNumber);
    public User linkBankAccount(String userId, String upiId, String bankName, String accountNumber, double balance);
}
