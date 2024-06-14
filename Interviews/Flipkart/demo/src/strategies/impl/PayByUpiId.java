package Interviews.Flipkart.demo.src.strategies.impl;

import Interviews.Flipkart.demo.src.model.BankAccount;
import Interviews.Flipkart.demo.src.repo.impl.BankRepoImpl;
import Interviews.Flipkart.demo.src.repo.impl.UserRepoImpl;
import Interviews.Flipkart.demo.src.strategies.PaymentStrategy;

public class PayByUpiId implements PaymentStrategy{
    String upiId;
    UserRepoImpl userRepoImpl;
    BankRepoImpl bankRepoImpl;


    public PayByUpiId(String UpiId, UserRepoImpl userRepoImpl, BankRepoImpl bankRepoImpl) {
        this.upiId = upiId;
        this.bankRepoImpl = bankRepoImpl;
        this.userRepoImpl = userRepoImpl;
    }

    public void pay(BankAccount bankAccount, double amount) {
        
    }
}
