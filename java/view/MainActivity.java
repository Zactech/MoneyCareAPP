package view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.moneycare.R;

import presenter.MainActivityPresenter;
import presenter.MainInterface;

public class MainActivity extends AppCompatActivity implements MainInterface {
    private MainActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        mPresenter = new MainActivityPresenter( this, this.getApplicationContext() );

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( this );
        if (!prefs.getBoolean( "firstTime", false )) {

            Intent intent = new Intent( MainActivity.this, InitialActivity.class );
            startActivity( intent );


            if (mPresenter.existUser()) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean( "firstTime", true );
                editor.apply();
            }

        }

        ImageView btnSpending = findViewById( R.id.btnSpending );
        ImageView btnBalance = findViewById( R.id.btnBalance );
        ImageView btnSpendingCategory = findViewById( R.id.btnSpendingCategory );
        ImageView btnSpendingMonth = findViewById( R.id.btnSpendingMonth );
        ImageView btnSpendingYear = findViewById( R.id.btnSpendingYear );
        ImageView btnAccount = findViewById( R.id.btnAccount );
        ImageView btnCard = findViewById( R.id.btnCard );
        ImageView btnDelete = findViewById( R.id.btnDelete );
        ImageView btnConfiguration = findViewById( R.id.btnConfiguration );

        //Renew balance and Credit for account and Credit Card
        mPresenter.renewalBalanceAccount();
        mPresenter.renewalCredit();

        btnSpending.setOnClickListener( v -> {
            if (mPresenter.existAccount() && mPresenter.existCard()) {
                Intent intent = new Intent( MainActivity.this, SpendingActivity.class );
                startActivity( intent );
            } else {
                Toast.makeText( MainActivity.this, getResources().getString( R.string.error_empty_account_card ), Toast.LENGTH_SHORT ).show();
            }
        } );

        btnBalance.setOnClickListener( v -> {
            if (mPresenter.existAccount() && mPresenter.existCard()) {
                Intent intent = new Intent( MainActivity.this, BalanceActivity.class );
                startActivity( intent );
            } else {
                Toast.makeText( MainActivity.this, getResources().getString( R.string.error_empty_account_card ), Toast.LENGTH_SHORT ).show();
            }
        } );

        btnSpendingCategory.setOnClickListener( v -> {
            if (mPresenter.existAccount() && mPresenter.existCard()) {
                Intent intent = new Intent( MainActivity.this, SpendingCategoryActivity.class );
                startActivity( intent );
            } else {
                Toast.makeText( MainActivity.this, getResources().getString( R.string.error_empty_account_card ), Toast.LENGTH_SHORT ).show();
            }
        } );

        btnSpendingMonth.setOnClickListener( v -> {
            if (mPresenter.existAccount() && mPresenter.existCard()) {
                Intent intent = new Intent( MainActivity.this, SpendingMonthActivity.class );
                startActivity( intent );
            } else {
                Toast.makeText( MainActivity.this, getResources().getString( R.string.error_empty_account_card ), Toast.LENGTH_SHORT ).show();
            }
        } );

        btnSpendingYear.setOnClickListener( v -> {
            if (mPresenter.existAccount() && mPresenter.existCard()) {
                Intent intent = new Intent( MainActivity.this, SpendingYearActivity.class );
                startActivity( intent );
            } else {
                Toast.makeText( MainActivity.this, getResources().getString( R.string.error_empty_account_card ), Toast.LENGTH_SHORT ).show();
            }
        } );

        btnAccount.setOnClickListener( v -> {
            Intent intent = new Intent( MainActivity.this, AccountActivity.class );
            startActivity( intent );
        } );

        btnCard.setOnClickListener( v -> {
            Intent intent = new Intent( MainActivity.this, CardActivity.class );
            startActivity( intent );
        } );

        btnDelete.setOnClickListener( v -> {
            if (mPresenter.existAccount() && mPresenter.existCard()) {
                Intent intent = new Intent( MainActivity.this, DeleteActivity.class );
                startActivity( intent );
            } else {
                Toast.makeText( MainActivity.this, getResources().getString( R.string.error_empty_account_card ), Toast.LENGTH_SHORT ).show();
            }
        } );

        btnConfiguration.setOnClickListener( v -> {
            if (mPresenter.existAccount() && mPresenter.existCard()) {
                Intent intent = new Intent( MainActivity.this, ConfigurationActivity.class );
                startActivity( intent );
            } else {
                Toast.makeText( MainActivity.this, getResources().getString( R.string.error_empty_account_card ), Toast.LENGTH_SHORT ).show();
            }
        } );
    }
}