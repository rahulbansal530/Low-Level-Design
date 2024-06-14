package Flipkart.UPISystem.src.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {

    private String id;
    private String name;
    private String phoneNumber;
    private List<BankAccount> linkedBankAccounts;
    private List<Transaction> transactions;

    public User(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.id = UUID.randomUUID().toString();
        linkedBankAccounts = new ArrayList<>();
        transactions = new ArrayList<>();
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public List<BankAccount> getLinkedBankAccounts() {
        return linkedBankAccounts;
    }
    public void setLinkedBankAccounts(List<BankAccount> linkedBankAccounts) {
        this.linkedBankAccounts = linkedBankAccounts;
    }

    public void addBankAccount(BankAccount bankAccount) {
        this.linkedBankAccounts.add(bankAccount);
    }
    
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", phoneNumber=" + phoneNumber + ", linkedBankAccounts="
                + linkedBankAccounts + "]";
    }
    public List<Transaction> getTransactions() {
        return transactions;
    }
    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    
}
