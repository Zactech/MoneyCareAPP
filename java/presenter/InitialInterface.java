package presenter;

public interface InitialInterface {
    String getName();
    String getEmail();
    void registrationError();
    void successfullyInserted();
    void DatabaseInsertError();
}
