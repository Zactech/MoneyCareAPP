package presenter;

public interface CardInterface {
    String getBankName();
    void registrationError();
    void successfullyInserted();
    void databaseInsertError();
    String getCardName();
    String getCredit();
    String getReceiptDate();
}
