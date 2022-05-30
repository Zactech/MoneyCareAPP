package view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.moneycare.R;

import java.util.ArrayList;

import presenter.CardActivityPresenter;
import presenter.CardInterface;

public class CardActivity extends AppCompatActivity implements CardInterface {
    private EditText cardName, credit;
    private Spinner bankName, spnRenewalCredit;
    private CardActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_card );

        cardName = findViewById(R.id.edtCardName);
        credit = findViewById(R.id.edtCredit);
        spnRenewalCredit = findViewById(R.id.spnRenewalCredit);
        bankName = findViewById(R.id.spnBankName);
        Button btnClean = findViewById( R.id.btnClean );
        Button btnSubmit = findViewById( R.id.btnSubmit );
        mPresenter = new CardActivityPresenter(this, this.getApplicationContext() );

        addRenewalCreditSpinner();
        addElementSpinner();

        btnClean.setOnClickListener( v -> clean() );

        btnSubmit.setOnClickListener( v -> {
            if (mPresenter.cardRegistration())
                finish();
        } );
    }

    public void addElementSpinner() {
        ArrayList<String> arrayAccountName = mPresenter.getAllAccountName();

        ArrayAdapter<String> adapter = new ArrayAdapter<>( getApplicationContext(), android.R.layout.simple_spinner_item, arrayAccountName );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bankName.setAdapter(adapter);
    }

    public void addRenewalCreditSpinner() {
        ArrayList<String> days = new ArrayList<>();

        for(int i = 1; i <= 31; i++)
            days.add( String.valueOf(i) );

        ArrayAdapter<String> adapter = new ArrayAdapter<>( getApplicationContext(), android.R.layout.simple_spinner_item, days );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnRenewalCredit.setAdapter(adapter);
    }

    public void clean() {
        cardName.requestFocus();
        cardName.setText(null);
        credit.setText(null);
        spnRenewalCredit.setSelection(0);
        bankName.setSelection(0);
    }

    public String getCardName() {
        return cardName.getText().toString();
    }

    public String getCredit() {
        return credit.getText().toString();
    }

    public String getReceiptDate() {
        return spnRenewalCredit.getSelectedItem().toString();
    }

    public String getBankName() {
        return bankName.getSelectedItem().toString();
    }

    public void registrationError() {
        Toast.makeText(CardActivity.this, getResources().getString(R.string.registration_error), Toast.LENGTH_SHORT).show();
    }

    public void successfullyInserted() {
        Toast.makeText(CardActivity.this, getResources().getString(R.string.successfully_registration), Toast.LENGTH_SHORT).show();
    }

    public void databaseInsertError() {
        Toast.makeText(CardActivity.this, getResources().getString(R.string.database_insert_error), Toast.LENGTH_SHORT).show();

    }
}