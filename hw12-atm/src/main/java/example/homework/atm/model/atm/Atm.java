package example.homework.atm.model.atm;

import example.homework.atm.model.banknote.Banknote;

import java.util.List;

public class Atm {

    private final AtmStorage atmStorage;

    public Atm(AtmStorage atmStorage) {
        this.atmStorage = atmStorage;
    }

    public List<Banknote> withdrawMoney(int sum) {
        return atmStorage.withdrawMoney(sum);
    }

    public void depositMoney(List<Banknote> banknoteList) {
        atmStorage.depositMoney(banknoteList);
    }

    public int getBalance() {
        return atmStorage.getBalance();
    }
}
