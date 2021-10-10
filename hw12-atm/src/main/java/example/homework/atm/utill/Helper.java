package example.homework.atm.utill;

import example.homework.atm.model.banknote.Banknote;

import java.util.List;

public class Helper {

    public static void log(String message) {
        new Helper().logger(message);
    }

    private void logger(String message) {
        System.out.println(message);
    }

    public static OperationSum getOperationSum(List<Banknote> banknoteList) {
        return new Helper().calculateBanknote(banknoteList);
    }

    private OperationSum calculateBanknote(List<Banknote> banknoteList) {
        return null;
    }
}
