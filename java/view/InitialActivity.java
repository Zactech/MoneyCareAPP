package view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moneycare.R;

import presenter.InitialActivityPresenter;
import presenter.InitialInterface;

public class InitialActivity extends AppCompatActivity implements InitialInterface {
    private EditText name, email;
    private InitialActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_initial);

        name = findViewById(R.id.edtName);
        email = findViewById(R.id.edtEmail);
        Button btSubmit = findViewById( R.id.btnSubmit );
        Button btClean = findViewById( R.id.btnClean );
        mPresenter = new InitialActivityPresenter(this, this.getApplicationContext());

        btClean.setOnClickListener( v -> cleanEditText() );

        btSubmit.setOnClickListener( v -> {
            if ( mPresenter.initialRegistration() ) {
                finish();
            }
        } );
    }

    // Clean EditText
    public void cleanEditText() {
        name.requestFocus();
        name.setText(null);
        email.setText(null);
    }

    public String getName() {
        return name.getText().toString();
    }

    public String getEmail() {
        return email.getText().toString();
    }

    public void registrationError() {
        Toast.makeText(InitialActivity.this, getResources().getString(R.string.registration_error), Toast.LENGTH_SHORT).show();
    }

    public void successfullyInserted() {
        Toast.makeText(InitialActivity.this, getResources().getString(R.string.successfully_registration), Toast.LENGTH_SHORT).show();
    }

    public void DatabaseInsertError() {
        Toast.makeText(InitialActivity.this, getResources().getString(R.string.database_insert_error), Toast.LENGTH_SHORT).show();
    }
}