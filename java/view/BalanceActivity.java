package view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.moneycare.R;

import java.util.ArrayList;

import presenter.BalanceActivityPresenter;
import presenter.BalanceInterface;

public class BalanceActivity extends AppCompatActivity implements BalanceInterface {

    private Spinner spnAccount, spnCard;
    private EditText edtBalance, edtCredit;
    private BalanceActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_balance );

        spnAccount = findViewById(R.id.spnAccount);
        spnCard = findViewById(R.id.spnCard);
        edtBalance = findViewById(R.id.edtBalance);
        edtBalance.setEnabled(false);
        edtCredit = findViewById(R.id.edtCredit);
        edtCredit.setEnabled(false);
        mPresenter = new BalanceActivityPresenter(this, this.getApplicationContext());
        addAccountSpinner();
        addCardSpinner();

        spnAccount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                edtBalance.setText( mPresenter.getBalanceAccount( spnAccount.getSelectedItem().toString() ) );
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        spnCard.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                edtCredit.setText( mPresenter.getCredit( spnCard.getSelectedItem().toString() ) );
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
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
}