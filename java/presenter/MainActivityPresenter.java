package presenter;


import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import dao.AccountDao;
import dao.CardDao;
import dao.ConfigurationAccountDao;
import dao.ConfigurationCardDao;
import dao.UserDao;
import model.Account;
import model.Card;
import model.ConfigurationAccount;
import model.ConfigurationCard;

public class MainActivityPresenter {
    MainInterface mView;
    Context c;
    Date date;
    int monthNow;
    int dayNow;
    boolean isFebruary, isThirtyOne;

    public MainActivityPresenter(MainInterface view, Context context) {
        mView = view;
        c = context;
        date = new Date(System.currentTimeMillis());
        monthNow = Integer.parseInt( new SimpleDateFormat("MM").format(date) );
        dayNow = Integer.parseInt( new SimpleDateFormat("dd").format(date) );

        isFebruary = isThirtyOne = false;

        if ( monthNow == 2) {
            isFebruary = true;
        } else if ( monthNow == 1 || monthNow == 3 || monthNow == 5 || monthNow == 7 || monthNow == 8 || monthNow == 10 || monthNow == 12 ) {
            isThirtyOne = true;
        }
    }

    public boolean existAccount() {
        AccountDao accountDao = new AccountDao(c);

        return accountDao.selectAccount().size() > 0;
    }

    public boolean existCard() {
        CardDao cardDao = new CardDao(c);

        return cardDao.selectCard().size() > 0;
    }

    public boolean existUser() {
        UserDao userDao = new UserDao(c);

        return userDao.selectUser() != null;
    }

    public void renewalBalanceAccount() {
        ConfigurationAccountDao config = new ConfigurationAccountDao(c);
        ArrayList<ConfigurationAccount> configAccount = config.selectConfigAccount();
        int day;

        for( ConfigurationAccount ca: configAccount ) {
            day = Integer.parseInt(ca.getReceiptDate());

            if ( (day == 29 || day == 30 || day == 31) && isFebruary ) {
                day = 28;
            } else if( day == 31 && !isThirtyOne ) {
                day = 30;
            }

            if ( day == dayNow ) {
                AccountDao acDao = new AccountDao(c);
                Account ac = (acDao.selectOnceAccount(ca.getBankName()));
                ac.setBalance( ac.getBalance() + ca.getBalance() );
                acDao.updateBalanceAccount(ac);
            }
        }
    }

    public void renewalCredit() {
        ConfigurationCardDao config = new ConfigurationCardDao(c);
        ArrayList<ConfigurationCard> configCard = config.selectConfigurationCard();
        int day;

        for( ConfigurationCard ca: configCard ) {
            day = Integer.parseInt(ca.getReceiptDate());

            if ( (day == 29 || day == 30 || day == 31) && isFebruary ) {
                day = 28;
            } else if( day == 31 && !isThirtyOne ) {
                day = 30;
            }

            if ( day == dayNow ) {
                CardDao caDao = new CardDao(c);
                Card card = (caDao.selectOnceCard(ca.getCardName()));
                card.setCredit( card.getCredit() + ca.getCredit() );
                caDao.updateCreditCard(card);
            }
        }
    }
}
