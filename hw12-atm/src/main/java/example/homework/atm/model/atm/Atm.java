package example.homework.atm.model.atm;

import example.homework.atm.model.banknote.Banknote;

import java.util.List;

public class Atm implements AtmInterface{

    private final AtmStorage atmStorage;

    public Atm(AtmStorage atmStorage) {
        this.atmStorage = atmStorage;
    }

    @Override
    public List<Banknote> withdrawMoney(int sum) {
        return atmStorage.withdrawMoney(sum);
    }

    @Override
    public void depositMoney(List<Banknote> banknoteList) {
        atmStorage.depositMoney(banknoteList);
    }

    @Override
    public int getBalance() {
        return atmStorage.getBalance();
    }
}
