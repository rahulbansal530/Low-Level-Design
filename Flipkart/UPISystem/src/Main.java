package Flipkart.UPISystem.src;

import Interviews.Flipkart.demo.src.model.User;
import Interviews.Flipkart.demo.src.repo.impl.BankRepoImpl;
import Interviews.Flipkart.demo.src.repo.impl.UserRepoImpl;
import Interviews.Flipkart.demo.src.service.UserService;
import Interviews.Flipkart.demo.src.service.impl.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserRepoImpl userRepoImpl = new UserRepoImpl();
        BankRepoImpl bankRepoImpl = new BankRepoImpl();

        UserService userService = new UserServiceImpl(userRepoImpl, bankRepoImpl);

        User user1 = userService.addUser("rahul", "8556040279");
        User user2 = userService.addUser("mohan", "1234567890");

        System.out.println(user1);
        System.out.println(user2);

        userService.linkBankAccount(user1.getId(), "testupi1", "HDFC", "12345", 500);
        userService.linkBankAccount(user2.getId(), "testupi1", "HDFC", "123456", 500);
        
        System.out.println(user1);
    }
}
