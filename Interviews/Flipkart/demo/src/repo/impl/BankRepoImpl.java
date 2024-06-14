package Interviews.Flipkart.demo.src.repo.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import Interviews.Flipkart.demo.src.exceptions.CustomException;
import Interviews.Flipkart.demo.src.model.BankAccount;

public class BankRepoImpl {
    Map<String, BankAccount> bankIdtoBank = new HashMap<>();

    public BankAccount getBankById(String id){
        BankAccount bankAccount = bankIdtoBank.get(id);
        if(Objects.isNull(id)) throw new CustomException("No bank found with Id: " + id);
        return bankAccount;
    }

    public BankAccount getBankByUpiId(String upiId){
        for (BankAccount bankAccount : bankIdtoBank.values()) {
            if (bankAccount.getUpiId().equals(upiId)) {
                return bankAccount;
            }
        }
        throw new CustomException("No bank found with upi id: " + upiId);
    }

    public BankAccount getBankByAccountNumber(String accountNumber){
        for (BankAccount bankAccount : bankIdtoBank.values()) {
            if (bankAccount.getAccountNumber().equals(accountNumber)) {
                return bankAccount;
            }
        }
        throw new CustomException("No bank found with account no.: " + accountNumber);
    }

    public void addBankAccount(String bankId, BankAccount bankAccount) {
        if (checkBankExists(bankAccount)) {
            throw new CustomException("Duplicate Bank Account");
        }
        bankIdtoBank.put(bankAccount.getId(), bankAccount);
    }

    public boolean checkBankExists(BankAccount bankAccount) {
        for (BankAccount u : bankIdtoBank.values()) {
            if (u.getAccountNumber().equals(bankAccount.getAccountNumber())) {
                return true;
            }
        }
        return false;
    }
}
