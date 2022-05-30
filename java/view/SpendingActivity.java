package view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.moneycare.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import presenter.SpendingActivityPresenter;
import presenter.SpendingInterface;

public class SpendingActivity extends AppCompatActivity implements SpendingInterface {
    private EditText description, amount, emissionDate;
    private Spinner spCategory, spAccount, spCard;
    private RadioButton rbDebit, rbCard;
    private SpendingActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_spending);

        description = findViewById(R.id.edtDescription);
        amount = findViewById(R.id.edtBalance);
        emissionDate = findViewById(R.id.edtEmissionDate);
        spCategory = findViewById(R.id.spnCategory);
        spAccount = findViewById(R.id.spnAccount);
        spCard = findViewById(R.id.spnCard);
        rbDebit = findViewById(R.id.rbDebit);
        rbCard = findViewById(R.id.rbCard);
        Button btnClean = findViewById( R.id.btnClean );
        Button btnSubmit = findViewById( R.id.btnSubmit );
        mPresenter = new SpendingActivityPresenter(this, this.getApplicationContext() );

        addCategorySpinner();
        addAccountSpinner();
        addCardSpinner();

        Date date = new Date( System.currentTimeMillis() );
        SimpleDateFormat fo = new SimpleDateFormat("dd/MM/yyyy");
        emissionDate.setText(fo.format( date ));
        emissionDate.setEnabled(false);

        btnClean.setOnClickListener( v -> clean() );

        btnSubmit.setOnClickListener( v -> {
            if ( mPresenter.spendingRegistration() )
                finish();
        } );
    }

    public void clean() {
        description.requestFocus();
        description.setText(null);
        amount.setText(null);
        emissionDate.setText(null);
        spCategory.setSelection(0);
        spAccount.setSelection(0);
        spCard.setSelection(0);
        rbDebit.setChecked(true);
    }

    public void addCategorySpinner() {
        final String[] arrayCategory = {
                this.getResources().getString(R.string.auto_transport),
                this.getResources().getString(R.string.bills),
                this.getResources().getString(R.string.business_services),
                this.getResources().getString(R.string.education),
                this.getResources().getString(R.string.entertainment),
                this.getResources().getString(R.string.food_dining),
                this.getResources().getString(R.string.gifts_donations),
                this.getResources().getString(R.string.health_fitness),
                this.getResources().getString(R.string.income),
                this.getResources().getString(R.string.investments),
                this.getResources().getString(R.string.kids),
                this.getResources().getString(R.string.other),
                this.getResources().getString(R.string.personal_care),
                this.getResources().getString(R.string.shopping),
                this.getResources().getString(R.string.taxes),
                this.getResources().getString(R.string.travel)
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>( getApplicationContext(), android.R.layout.simple_spinner_item, arrayCategory );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategory.setAdapter(adapter);
    }

    public void addAccountSpinner() {
        ArrayList<String> arrayAccountName = mPresenter.getAllAccountName();

        ArrayAdapter<String> adapter = new ArrayAdapter<>( getApplicationContext(), android.R.layout.simple_spinner_item, arrayAccountName );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAccount.setAdapter(adapter);
    }

    public void addCardSpinner() {
        ArrayList<String>arrayCardName = mPresenter.getAllCardName();

        ArrayAdapter<String> adapter = new ArrayAdapter<>( getApplicationContext(), android.R.layout.simple_spinner_item, arrayCardName );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCard.setAdapter(adapter);
    }

    public String getDescription() {
        return description.getText().toString();
    }

    public String getAmount() {
        return amount.getText().toString();
    }

    public String getEmissionDate() {
        return emissionDate.getText().toString();
    }

    public String getCategory() {
        return spCategory.getSelectedItem().toString();
    }

    public String getAccount() {
        return spAccount.getSelectedItem().toString();
    }

    public String getCard() {
        return spCard.getSelectedItem().toString();
    }

    public String getChoose() {
        if(rbCard.isChecked())
            return rbCard.getText().toString();
        else
            return rbDebit.getText().toString();
    }

    public void successfullyInserted() {
        Toast.makeText(SpendingActivity.this, getResources().getString(R.string.successfully_registration), Toast.LENGTH_SHORT).show();
    }

    public void databaseInsertError() {
        Toast.makeText(SpendingActivity.this, getResources().getString(R.string.database_insert_error), Toast.LENGTH_SHORT).show();
    }

    public void registrationError() {
        Toast.makeText(SpendingActivity.this, getResources().getString(R.string.registration_error), Toast.LENGTH_SHORT).show();
    }
}