package presenter;

public interface DeleteInterface {
    String getAccountSpinner();
    void successfullyDeleted();
    void databaseInsertError();
    String getCardSpinner();
    String getSpendingSpinner();
}

