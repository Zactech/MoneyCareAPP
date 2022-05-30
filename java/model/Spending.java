package model;

public class Spending {
    private final int spendingCod;
    private final String description;
    private String category;
    private final float amount;
    private final String emissionDate;
    private String bankName;
    private String cardName;

    public Spending(int spendingCod, String description, float amount, String emissionDate, String category,
                    String bankName, String cardName) {
        this.spendingCod = spendingCod;
        this.description = description;
        this.category = category;
        this.amount = amount;
        this.emissionDate = emissionDate;
        this.bankName = bankName;
        this.cardName = cardName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public int getSpendingCod() {
        return spendingCod;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getAmount() {
        return amount;
    }

    public String getEmissionDate() {
        return emissionDate;
    }
}
