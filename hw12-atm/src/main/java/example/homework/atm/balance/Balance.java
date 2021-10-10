package example.homework.atm.balance;

public class Balance {
    private static int balance = 1_000;

    public static int getBalance() {
        return balance;
    }

    public static void setBalance(int val) {
        balance = val;
    }
}
