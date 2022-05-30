package view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.moneycare.R;

import java.util.ArrayList;

import model.ConfigurationAccount;
import model.ConfigurationCard;
import presenter.ConfigurationActivityPresenter;
import presenter.ConfigurationInterface;

public class ConfigurationActivity extends AppCompatActivity implements ConfigurationInterface {
    private Spinner spnAccount, spnCard, spnRenewalBalance, spnRenewalCredit;
    private EditText edtBalance, edtCredit;
    private Button btnAlterAccount, btnAlterCredit;
    private ConfigurationActivityPresenter mPresenter;
    private ConfigurationAccount confAccount;
    private ConfigurationCard confCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        spnAccount = (Spinner) findViewById(R.id.spnAccount);
        spnCard = (Spinner) findViewById(R.id.spnCard);
        spnRenewalBalance = (Spinner) findViewById(R.id.spnRenewalBalance);
        spnRenewalCredit = (Spinner) findViewById(R.id.spnRenewalCredit);
        edtBalance = (EditText) findViewById(R.id.edtBalance);
        edtCredit = (EditText) findViewById(R.id.edtCredit);
        btnAlterAccount = (Button) findViewById(R.id.btnAlterAccount);
        btnAlterCredit = (Button) findViewById(R.id.btnAlterCredit);
        mPresenter = new ConfigurationActivityPresenter(this, this.getApplicationContext());

        //Add elements in Spinner
        addAccountSpinner();
        addCardSpinner();
        addRenewalBalanceSpinner();
        addRenewalCreditSpinner();

        spnAccount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                confAccount = mPresenter.getConfAccount(spnAccount.getSelectedItem().toString());

                if (confAccount != null) {
                    spnRenewalBalance.setSelection(Integer.parseInt(confAccount.getReceiptDate()));
                    edtBalance.setText(String.valueOf(confAccount.getBalance()));
                } else {
                    spnRenewalBalance.setSelection(0);
                    edtBalance.setText("0.0");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });

        spnCard.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                confCard = mPresenter.getConfCard(spnCard.getSelectedItem().toString());

                if ( confCard != null ) {
                    spnRenewalCredit.setSelection(Integer.parseInt(confCard.getReceiptDate()));
                    edtCredit.setText(String.valueOf(confCard.getCredit()) );
                } else {
                    spnRenewalCredit.setSelection(0);
                    edtCredit.setText("0.0");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });

        btnAlterAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.registrationConfigurationAccount();
            }
        });


        btnAlterCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.registrationConfigurationCard();
            }
        });
    }

    public void addAccountSpinner() {
        ArrayList<String> arrayAccountName = mPresenter.getAllAccountName();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayAccountName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnAccount.setAdapter(adapter);
    }

    public void addCardSpinner() {
        ArrayList<String> arrayAccountName = mPresenter.getAllCardName();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayAccountName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCard.setAdapter(adapter);
    }

    public void addRenewalBalanceSpinner() {
        ArrayList<String> days = new ArrayList<>();

        for(int i = 1; i <= 31; i++)
            days.add( String.valueOf(i) );

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, days);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnRenewalBalance.setAdapter(adapter);
    }

    public void addRenewalCreditSpinner() {
        ArrayList<String> days = new ArrayList<>();

        for(int i = 1; i <= 31; i++)
            days.add( String.valueOf(i) );

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, days);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnRenewalCredit.setAdapter(adapter);
    }

    public String getAccountSpinner() {
        return spnAccount.getSelectedItem().toString();
    }

    public String getBalance() {
        return edtBalance.getText().toString();
    }

    public String getRenewalBalance() {
        return spnRenewalBalance.getSelectedItem().toString();
    }

    public String getCardSpinner() {
        return spnCard.getSelectedItem().toString();
    }

    public String getCredit() {
        return edtCredit.getText().toString();
    }

    public String getRenewalCredit() {
        return spnRenewalCredit.getSelectedItem().toString();
    }

    public void updatedSuccessfully() {
        Toast.makeText(ConfigurationActivity.this, getResources().getString(R.string.configuration_update_successfully), Toast.LENGTH_SHORT).show();
    }

    public void databaseInsertError() {
        Toast.makeText(ConfigurationActivity.this, getResources().getString(R.string.database_insert_error), Toast.LENGTH_SHORT).show();
    }

    public void registrationError() {
        Toast.makeText(ConfigurationActivity.this, getResources().getString(R.string.registration_error), Toast.LENGTH_SHORT).show();
    }
}