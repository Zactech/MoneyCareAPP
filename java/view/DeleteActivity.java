package view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.moneycare.R;

import java.util.ArrayList;

import presenter.DeleteActivityPresenter;
import presenter.DeleteInterface;

public class DeleteActivity extends AppCompatActivity implements DeleteInterface {
    private Spinner spnAccount, spnCard, spnSpending;
    private DeleteActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_delete);

        mPresenter = new DeleteActivityPresenter(this, this.getApplicationContext());

        spnCard = findViewById(R.id.spnDeleteCard);
        spnAccount = findViewById(R.id.spnDeleteAccount);
        spnSpending = findViewById(R.id.spnDeleteSpending);
        Button btnDeleteCard = findViewById( R.id.btnDeleteCard );
        Button btnDeleteAccount = findViewById( R.id.btnDeleteAccount );
        Button btnDeleteSpending = findViewById( R.id.btnDeleteSpending );

        addCardSpinner();
        addAccountSpinner();
        addSpendingSpinner();

        btnDeleteCard.setOnClickListener( v -> mPresenter.deleteCard() );

        btnDeleteAccount.setOnClickListener( v -> mPresenter.deleteAccount() );

        btnDeleteSpending.setOnClickListener( v -> mPresenter.deleteSpending() );
    }

    public void addAccountSpinner() {
        ArrayList<String> arrayAccountName = mPresenter.getAllAccountName();

        ArrayAdapter<String> adapter = new ArrayAdapter<>( getApplicationContext(), android.R.layout.simple_spinner_item, arrayAccountName );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnAccount.setAdapter(adapter);
    }

    public void addCardSpinner() {
        ArrayList<String> arrayCardName = mPresenter.getAllCardName();

        ArrayAdapter<String> adapter = new ArrayAdapter<>( getApplicationContext(), android.R.layout.simple_spinner_item, arrayCardName );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCard.setAdapter(adapter);
    }

    public void addSpendingSpinner() {
        ArrayList<String> arraySpendingName = mPresenter.getAllSpendingName();

        ArrayAdapter<String> adapter = new ArrayAdapter<>( getApplicationContext(), android.R.layout.simple_spinner_item, arraySpendingName );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnSpending.setAdapter(adapter);
    }

    public String getCardSpinner() {
        return spnCard.getSelectedItem().toString();
    }

    public String getAccountSpinner() {
        return spnAccount.getSelectedItem().toString();
    }

    public String getSpendingSpinner() {
        return spnSpending.getSelectedItem().toString();
    }

    public void successfullyDeleted() {
        Toast.makeText(DeleteActivity.this,  getResources().getString(R.string.successfully_delete), Toast.LENGTH_SHORT).show();
    }

    public void databaseInsertError() {
        Toast.makeText(DeleteActivity.this, getResources().getString(R.string.successfully_delete), Toast.LENGTH_SHORT).show();
    }

}