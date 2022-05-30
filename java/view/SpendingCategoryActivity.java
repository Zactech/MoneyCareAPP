package view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.moneycare.R;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import presenter.SpendingCategoryActivityPresenter;
import presenter.SpendingCategoryInterface;

public class SpendingCategoryActivity extends AppCompatActivity implements SpendingCategoryInterface {
    private PieChart pieChart;
    private String monthYear;
    private SpendingCategoryActivityPresenter mPresenter;
    private ArrayList<String> arrayCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_spending_category);

        mPresenter = new SpendingCategoryActivityPresenter(this, this.getApplicationContext() );

        Date date = new Date( System.currentTimeMillis() );
        SimpleDateFormat format = new SimpleDateFormat("MM/yyyy");
        monthYear = format.format( date );

        arrayCategory = new ArrayList<>();
        arrayCategory.add(getResources().getString(R.string.auto_transport));
        arrayCategory.add(getResources().getString(R.string.bills));
        arrayCategory.add(getResources().getString(R.string.business_services));
        arrayCategory.add(getResources().getString(R.string.education));
        arrayCategory.add(getResources().getString(R.string.entertainment));
        arrayCategory.add(getResources().getString(R.string.food_dining));
        arrayCategory.add(getResources().getString(R.string.gifts_donations));
        arrayCategory.add(getResources().getString(R.string.health_fitness));
        arrayCategory.add(getResources().getString(R.string.income));
        arrayCategory.add(getResources().getString(R.string.investments));
        arrayCategory.add(getResources().getString(R.string.kids));
        arrayCategory.add(getResources().getString(R.string.other));
        arrayCategory.add(getResources().getString(R.string.personal_care));
        arrayCategory.add(getResources().getString(R.string.shopping));
        arrayCategory.add(getResources().getString(R.string.taxes));
        arrayCategory.add(getResources().getString(R.string.travel));


        pieChart = findViewById(R.id.pieChart);
        createCategoryPieChart();
    }

    // Create PieChart and put all Spending by Category
    public void createCategoryPieChart() {
        Float[] spendingForCategory = mPresenter.spendingForCategory(monthYear);
        ArrayList<String> categoryWithSpending = new ArrayList<>();
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        PieDataSet dataSet;

        for ( int i = 0; i < spendingForCategory.length; i++ ) {
            if( spendingForCategory[i] > 0 ) {
                entries.add( new PieEntry(( float ) spendingForCategory[i], i ));
                categoryWithSpending.add(arrayCategory.get(i));
            }
        }

        dataSet = new PieDataSet(entries, getResources().getString(R.string.category));
        dataSet.setColors( ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.getDescription().setText( "Spending Category" );
        pieChart.getDescription().setEnabled(   true );
        pieChart.setCenterText( "Category" );

    }

    public ArrayList<String> getCategory() {
        return arrayCategory;
    }
}