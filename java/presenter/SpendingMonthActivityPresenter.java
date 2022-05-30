package presenter;


import android.content.Context;

import java.util.ArrayList;

import dao.SpendingDao;
import model.Spending;

public class SpendingMonthActivityPresenter {
    private final SpendingMonthInterface mView;
    SpendingDao spendingDao;

    public SpendingMonthActivityPresenter(SpendingMonthInterface view, Context context) {
        mView = view;
        spendingDao = new SpendingDao( context );
    }

    public ArrayList<Spending> getSpendingForMonthYear() {
        String monthYear = mView.getMonthYear();

        return spendingDao.selectSpendingMonthYear(monthYear);
    }
}

