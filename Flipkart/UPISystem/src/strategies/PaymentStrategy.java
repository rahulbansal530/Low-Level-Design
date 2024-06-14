package Flipkart.UPISystem.src.strategies;

import Interviews.Flipkart.demo.src.model.BankAccount;

public interface PaymentStrategy {
    void pay(BankAccount payer, double amount);
}
