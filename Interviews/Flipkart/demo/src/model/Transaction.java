package Interviews.Flipkart.demo.src.model;

import java.util.UUID;

public class Transaction {
    private String id;
    private String scrBankID;
    private String destBankId;
    private long timeStamp;
    private double amonut;


    public Transaction(String scrBankID, String destBankId, double amonut) {
        this.scrBankID = scrBankID;
        this.destBankId = destBankId;
        this.amonut = amonut;
        this.id = UUID.randomUUID().toString();
        this.timeStamp = System.currentTimeMillis();
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getScrBankID() {
        return scrBankID;
    }
    public void setScrBankID(String scrBankID) {
        this.scrBankID = scrBankID;
    }
    public String getDestBankId() {
        return destBankId;
    }
    public void setDestBankId(String destBankId) {
        this.destBankId = destBankId;
    }
    public long getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
    public double getAmonut() {
        return amonut;
    }
    public void setAmonut(double amonut) {
        this.amonut = amonut;
    }

    @Override
    public String toString() {
        return "Transaction [id=" + id + ", scrBankID=" + scrBankID + ", destBankId=" + destBankId + ", timeStamp="
                + timeStamp + ", amonut=" + amonut + "]";
    }

    

    
}
