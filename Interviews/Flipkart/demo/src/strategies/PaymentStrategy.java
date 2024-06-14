package Interviews.Flipkart.demo.src.strategies;

import Interviews.Flipkart.demo.src.model.BankAccount;
import Interviews.Flipkart.demo.src.repo.impl.UserRepoImpl;
import Interviews.Flipkart.demo.src.repo.impl.BankRepoImpl;

public interface PaymentStrategy {
    void pay(BankAccount payer, double amount);
}
