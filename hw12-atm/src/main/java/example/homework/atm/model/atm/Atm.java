package example.homework.atm.model.atm;

import example.homework.atm.model.banknote.Banknote;
import example.homework.atm.model.banknote.BanknoteSeparator;

import java.util.List;

public class Atm {

    private final AtmStorage atmStorage;
    private final BanknoteSeparator banknoteSeparator;

    public Atm(AtmStorage atmStorage, BanknoteSeparator banknoteSeparator) {
        this.atmStorage = atmStorage;
        this.banknoteSeparator = banknoteSeparator;
    }

    public List<Banknote> withdrawMoney(int sum) {
        List<Banknote> banknoteList = banknoteSeparator.sort(sum);
        atmStorage.withdrawMoney(banknoteList);
        return banknoteList;
    }

    public void depositMoney(List<Banknote> banknoteList) {
        atmStorage.depositMoney(banknoteList);
    }

    public int getBalance() {
        return atmStorage.getBalance();
    }
}
