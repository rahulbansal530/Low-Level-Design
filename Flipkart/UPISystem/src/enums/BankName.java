package Flipkart.UPISystem.src.enums;

public enum BankName {
    HDFC(1), IDFC(0);

    private BankName(int serverStatus) {
        this.serverStatus = serverStatus;
    }

    private int serverStatus;

    public int getServerStatus() {
        return this.serverStatus;
    }

}
