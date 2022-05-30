package model;

public class ConfigurationAccount {
    private String bankName;
    private final String receiptDate;
    private double balance;

    public ConfigurationAccount(String bankName, double balance, String receiptDate) {
        this.bankName = bankName;
        this.balance = balance;
        this.receiptDate = receiptDate;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public String getReceiptDate() {
        return receiptDate;
    }
}
