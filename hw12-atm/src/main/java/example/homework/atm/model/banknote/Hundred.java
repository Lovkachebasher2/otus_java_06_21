package example.homework.atm.model.banknote;

public class Hundred implements Banknote {
    private final int count;

    public Hundred(int count) {
        this.count = count;
    }

    public int getCount() {
        return this.count;
    }

    @Override
    public int calculateSum() {
        return this.count * 100;
    }
}
