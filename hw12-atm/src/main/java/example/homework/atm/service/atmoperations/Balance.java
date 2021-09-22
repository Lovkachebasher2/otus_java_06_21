package example.homework.atm.service.atmoperations;

public class Balance {
    private static int balance = 1000;

    public static int getBalance() {
        return balance;
    }

    public static void addBalance(int val) {
        balance = balance + val;
    }

    public static void outBalance(int val) {
        balance = balance - val;
    }
}
