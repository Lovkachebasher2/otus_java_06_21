package example.homework;

import example.homework.atm.Atm;
import example.homework.atm.model.banknote.Banknote;
import example.homework.atm.model.banknote.Hundred;
import example.homework.atm.model.banknote.Thousand;
import example.homework.atm.service.AtmOperationService;
import example.homework.atm.service.AtmOperationServiceImpl;
import example.homework.atm.utill.OperationSum;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Banknote> banknoteList = List.of(
                new Thousand(15),
                new Hundred(11)
        );
        AtmOperationService atmOperationService = new AtmOperationServiceImpl();

        Atm atm = new Atm(atmOperationService);
        atm.addBankAccount(new OperationSum(banknoteList));

        atm.outBankAccount(new OperationSum(1500));
    }
}
