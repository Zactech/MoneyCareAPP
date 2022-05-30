package view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.moneycare.R;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import dao.UserDao;
import model.Spending;
import model.User;
import presenter.SpendingMonthActivityPresenter;
import presenter.SpendingMonthInterface;

public class SpendingMonthActivity extends AppCompatActivity implements SpendingMonthInterface {
    private Spinner spnMonth;
    private EditText edtTotal;
    private TableLayout tbSpending;
    private SpendingMonthActivityPresenter mPresenter;
    private ArrayList<Spending> spending;
    private UserDao userDao;
    private float total;
    private final ArrayList<String> monthYear = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_spending_month);

        monthYear.add(getResources().getString(R.string.january));
        monthYear.add(getResources().getString(R.string.february));
        monthYear.add(getResources().getString(R.string.march));
        monthYear.add(getResources().getString(R.string.april));
        monthYear.add(getResources().getString(R.string.may));
        monthYear.add(getResources().getString(R.string.june));
        monthYear.add(getResources().getString(R.string.july));
        monthYear.add(getResources().getString(R.string.august));
        monthYear.add(getResources().getString(R.string.september));
        monthYear.add(getResources().getString(R.string.october));
        monthYear.add(getResources().getString(R.string.november));
        monthYear.add(getResources().getString(R.string.december));

        spnMonth = findViewById(R.id.spnMonth);
        edtTotal = findViewById(R.id.edtTotal);
        tbSpending = findViewById(R.id.tableSpending);
        mPresenter = new SpendingMonthActivityPresenter(this, this.getApplicationContext());
        spending = new ArrayList<>();
        addMonthSpinner();
        userDao = new UserDao(this);
        User user = userDao.selectUser();
        total = 0.0f;

        spnMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                addSpendingTableLayout();
                setTotalAmount();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


    }

    public void addMonthSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>( getApplicationContext(), android.R.layout.simple_spinner_item, monthYear );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnMonth.setAdapter(adapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addSpendingTableLayout() {
        spending = mPresenter.getSpendingForMonthYear();

        // Order by Category
        Collections.sort(spending, Comparator.comparing( Spending::getCategory ) );

        while (tbSpending.getChildCount() > 1) {
            int i = tbSpending.getChildCount() - 1;
            tbSpending.removeViewAt(i--);
        }

        for( int k = spending.size() -1; k > 0; k-- ) {
            int i = 1;

            TableRow row = new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);

            TextView txtSpending = new TextView(this);
            txtSpending.setText(String.valueOf( spending.get(k).getAmount()));

            TextView txtDate = new TextView(this);
            txtDate.setText( spending.get(k).getEmissionDate() );

            TextView txtCategory = new TextView(this);
            txtCategory.setPadding(40, 0, 0, 0);
            txtCategory.setText( spending.get(k).getCategory() );

            row.addView(txtSpending);
            row.addView(txtDate);
            row.addView(txtCategory);
            tbSpending.addView(row, i);
            i++;
        }
    }

    public String getMonthYear() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy");

        int month = spnMonth.getSelectedItemPosition();
        month++;

        if ( month >= 1 && month <= 9 ) {
            return ( "0" + month + "/" + format.format(date) );
        } else {
            return (month + "/" + format.format(date) );
        }
    }

    public void setTotalAmount() {
        total = 0.0f;

        for ( Spending sp: spending ){
            total += sp.getAmount();
        }

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        edtTotal.setText( df.format( total ) );
    }

}