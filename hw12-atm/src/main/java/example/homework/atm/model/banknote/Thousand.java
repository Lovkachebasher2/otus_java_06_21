package example.homework.atm.model.banknote;

public class Thousand implements Banknote {
    private final int count;

    public Thousand(int count) {
        this.count = count;
    }

    public int getCount() {
        return this.count;
    }

    @Override
    public int calculateSum() {
        return this.count*1000;
    }
}
