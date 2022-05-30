package presenter;


import android.content.Context;

import java.text.DecimalFormat;
import java.util.ArrayList;

import dao.AccountDao;
import dao.CardDao;
import model.Account;
import model.Card;

public class BalanceActivityPresenter {
    BalanceInterface mView;
    Context c;
    CardDao card;
    AccountDao account;
    DecimalFormat df;

    public BalanceActivityPresenter(BalanceInterface view, Context context) {
        mView = view;
        c = context;
        card = new CardDao(c);
        account = new AccountDao(c);
        df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
    }

    public String getBalanceAccount(String bankName) {
        Account acc = this.account.selectOnceAccount(bankName);
        return df.format( acc.getBalance() );
    }

    public String getCredit(String cardName) {
        Card ca = this.card.selectOnceCard(cardName);
        return df.format( ca.getCredit() );
    }

    public ArrayList<String> getAllCardName() {
        ArrayList<Card> ca = card.selectCard();
        ArrayList<String> arrayCard = new ArrayList<>();

        for ( Card a: ca ) {
            arrayCard.add(a.getCardName());
        }

        return arrayCard;
    }

    public ArrayList<String> getAllAccountName() {
        ArrayList<Account> ac = account.selectAccount();
        ArrayList<String> arrayAccount = new ArrayList<>();

        for ( Account a: ac ) {
            arrayAccount.add( a.getBankName() );
        }

        return arrayAccount;
    }

}
