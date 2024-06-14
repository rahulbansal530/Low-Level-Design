package Interviews.Flipkart.demo.src.service.impl;

import Interviews.Flipkart.demo.src.enums.BankAccountType;
import Interviews.Flipkart.demo.src.enums.BankName;
import Interviews.Flipkart.demo.src.exceptions.CustomException;
import Interviews.Flipkart.demo.src.model.BankAccount;
import Interviews.Flipkart.demo.src.model.User;
import Interviews.Flipkart.demo.src.repo.impl.BankRepoImpl;
import Interviews.Flipkart.demo.src.repo.impl.UserRepoImpl;
import Interviews.Flipkart.demo.src.service.UserService;

public class UserServiceImpl implements UserService{

    private UserRepoImpl userRepo;
    private BankRepoImpl bankRepo;

    public UserServiceImpl(UserRepoImpl userRepo, BankRepoImpl bankRepo){
        this.userRepo = userRepo;
        this.bankRepo = bankRepo;
    }

    public User addUser(String name, String phoneNumber) {
        User user = new User(name, phoneNumber);
        userRepo.addUser(user);
        return user;
    }

    public User linkBankAccount(String userId, String upiId, String bankName, String accountNumber, double balance) {
        
        if (!isValidBank(bankName)) {
            throw new CustomException("Not a valid bank");
        }
        User user = userRepo.getUserById(userId);

        BankAccountType bankAccountType = null;
        if (user.getLinkedBankAccounts().isEmpty()) {
            bankAccountType = BankAccountType.PRIMARY;
        } else {
            bankAccountType = BankAccountType.SECONDARY;
        }
        BankAccount bankAccount = new BankAccount(upiId, userId, BankName.valueOf(bankName), accountNumber, balance, bankAccountType);
        bankRepo.addBankAccount(bankAccount.getId(), bankAccount);
        return userRepo.addBankAccount(userId, bankAccount);
    }

    public void makeBankAccountPrimary(String userId, String bank) {
        
    }

    private boolean isValidBank(String bankName) {
        boolean isValidBank = false;;
        for (BankName name : BankName.values()) {
            if (name.name().equalsIgnoreCase(bankName))
            isValidBank = true;
        }
        return isValidBank;
    }

}
