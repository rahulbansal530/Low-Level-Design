package Interviews.Flipkart.demo.src.model;

import java.util.UUID;

import Interviews.Flipkart.demo.src.enums.BankAccountType;
import Interviews.Flipkart.demo.src.enums.BankName;

public class BankAccount {
    private String id;
    private String upiId;
    private String userId;
    private BankName bankName;
    private String accountNumber;
    private double balance;
    private BankAccountType bankAccountType;

    
    public BankAccount(String upiId, String userId, BankName bankName, String accountNumber, double balance, BankAccountType bankAccountType) {
        this.upiId = upiId;
        this.userId = userId;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.bankAccountType = bankAccountType;
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUpiId() {
        return upiId;
    }
    public void setUpiId(String upiId) {
        this.upiId = upiId;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public BankName getBankName() {
        return bankName;
    }
    public void setBankName(BankName bankName) {
        this.bankName = bankName;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "BankAccount [id=" + id + ", upiId=" + upiId + ", userId=" + userId + ", bankName=" + bankName
                + ", accountNumber=" + accountNumber + ", balance=" + balance + ", bankAccountType=" + bankAccountType
                + "]";
    }

    
}
