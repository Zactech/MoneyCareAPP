package presenter;

public interface ConfigurationInterface {
    String getAccountSpinner();
    String getBalance();
    String getRenewalBalance();
    String getCardSpinner();
    String getCredit();
    String getRenewalCredit();
    void updatedSuccessfully();
    void databaseInsertError();
    void registrationError();
}
