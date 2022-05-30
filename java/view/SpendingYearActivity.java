package view;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.example.moneycare.R;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import presenter.SpendingYearActivityPresenter;
import presenter.SpendingYearInterface;

public class SpendingYearActivity extends AppCompatActivity implements SpendingYearInterface {
    private BarChart barChart;
    private Date date;
    private GregorianCalendar dateCal;
    private SpendingYearActivityPresenter mPresenter;
    private  ArrayList<String> monthYear = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_spending_year);

        barChart = findViewById(R.id.barChart);
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

        mPresenter = new SpendingYearActivityPresenter(this, this.getApplicationContext());
        Date date = new Date( System.currentTimeMillis() );
        GregorianCalendar dateCal = new GregorianCalendar();
        dateCal.setTime( date );
        String.valueOf( dateCal.get( Calendar.MONTH ) );
        String.valueOf( dateCal.get( Calendar.YEAR ) );


        createMonthBarChart();

    }

    public void createMonthBarChart() {
        Float[] spendingMonthYear = mPresenter.AllSpendingForMonth();
        ArrayList<BarEntry> entries = new ArrayList<>();
        BarDataSet dataSet;

        for ( int i = 0; i < spendingMonthYear.length; i++ ) {
            entries.add( new BarEntry( spendingMonthYear[i], i) );
        }

        dataSet = new BarDataSet(entries, getResources().getString(R.string.month));
        dataSet.setColors( ColorTemplate.MATERIAL_COLORS);
        dataSet.setValueTextColor( Color.BLACK );
        dataSet.setValueTextSize( 14f );
        BarData barData = new BarData(dataSet);
        //data = new BarData( (IBarDataSet) monthYear, dataSet );
        barChart.setFitBars( true );
        barChart.setData(barData);
        barChart.getDescription().setText( getResources().getString(R.string.spending_by_month) );
        barChart.animateY(2000);

    }

    public ArrayList<String> MonthYear() {
        return this.monthYear;
    }

    @Override
    public ArrayList<String> monthYear() {
        return null;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public GregorianCalendar getDateCal() {
        return dateCal;
    }

    public void setDateCal(GregorianCalendar dateCal) {
        this.dateCal = dateCal;
    }
}