package Interviews.Flipkart.demo.src.service.impl;

import Interviews.Flipkart.demo.src.model.BankAccount;
import Interviews.Flipkart.demo.src.repo.impl.BankRepoImpl;
import Interviews.Flipkart.demo.src.repo.impl.UserRepoImpl;
import Interviews.Flipkart.demo.src.strategies.PaymentStrategy;
import Interviews.Flipkart.demo.src.strategies.impl.PayByUpiId;

public class PaymentServiceImpl {

    UserRepoImpl userRepoImpl;
    BankRepoImpl bankRepoImpl;

    public PaymentServiceImpl(UserRepoImpl userRepoImpl, BankRepoImpl bankRepoImpl) {
        this.bankRepoImpl = bankRepoImpl;
        this.userRepoImpl = userRepoImpl;
    }

    public void pay(BankAccount payer, String upiId, String accountNumber, String userId, double amount) {
        PaymentStrategy paymentStrategy = null;
        if (upiId != null) {
            paymentStrategy = new PayByUpiId(upiId, userRepoImpl, bankRepoImpl);
        }
        // else if conditions for phone number userId
        paymentStrategy.pay(payer, amount);
    }
}
