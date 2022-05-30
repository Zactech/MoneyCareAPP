package presenter;

public interface SpendingInterface {
    String getDescription();
    String getAmount();
    String getEmissionDate();
    String getCategory();
    String getAccount();
    String getCard();
    String getChoose();
    void successfullyInserted();
    void databaseInsertError();
    void registrationError();
}
