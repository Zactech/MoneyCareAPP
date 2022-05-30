package presenter;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import dao.AccountDao;
import dao.CardDao;
import dao.SpendingDao;
import model.Account;
import model.Card;
import model.Spending;

public class DeleteActivityPresenter {
    DeleteInterface mView;
    Context c;

    public DeleteActivityPresenter(DeleteInterface view, Context context) {
        mView = view;
        c = context;
    }

    public ArrayList<String> getAllCardName() {
        CardDao card = new CardDao(c);
        ArrayList<Card> ca = card.selectCard();
        ArrayList<String> arrayCard = new ArrayList<>();

        for ( Card a: ca ) {
            arrayCard.add( a.getCardName() );
        }
        return arrayCard;
    }

    public ArrayList<String> getAllAccountName() {
        AccountDao account = new AccountDao(c);
        ArrayList<Account> ac =account.selectAccount();
        ArrayList<String> arrayAccount = new ArrayList<>();

        for ( Account a: ac ) {
            arrayAccount.add( a.getBankName() );
        }

        return arrayAccount;
    }

    public ArrayList<String> getAllSpendingName() {
        SpendingDao spending = new SpendingDao(c);
        Date date = new Date(System.currentTimeMillis());
        String monthYear = new SimpleDateFormat("MM/yyyy").format(date);
        ArrayList<Spending> sp = spending.selectSpendingMonthYear(monthYear);
        ArrayList<String> arraySpending = new ArrayList<>();

        for ( Spending s: sp ) {
            arraySpending.add( s.getDescription() + "-" + s.getSpendingCod() );
        }

        return arraySpending;
    }

    public void deleteCard() {
        CardDao card = new CardDao(c);
        String cardName = mView.getCardSpinner();
        int result = card.deleteCard(cardName);

        if ( result > 0 )
            mView.successfullyDeleted();
        else
            mView.databaseInsertError();
    }

    public void deleteAccount() {
        AccountDao account = new AccountDao(c);
        String bankName = mView.getAccountSpinner();
        int result = account.deleteAccount(bankName);

        if ( result > 0 )
            mView.successfullyDeleted();
        else
            mView.databaseInsertError();
    }

    public void deleteSpending() {
        SpendingDao spending = new SpendingDao(c);
        String[] parts = mView.getSpendingSpinner().split("-");
        String spendingCod = parts[1];
        int result = spending.deleteSpending( Integer.parseInt(spendingCod) );

        if ( result > 0 )
            mView.successfullyDeleted();
        else
            mView.databaseInsertError();
    }


}
