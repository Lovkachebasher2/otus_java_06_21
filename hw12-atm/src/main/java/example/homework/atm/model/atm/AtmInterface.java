package example.homework.atm.model.atm;

import example.homework.atm.model.banknote.Banknote;

import java.util.List;

public interface AtmInterface {

    List<Banknote> withdrawMoney(int sum);

    void depositMoney(List<Banknote> banknoteList);

    int getBalance();
}
