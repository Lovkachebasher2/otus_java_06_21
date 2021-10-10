package example.homework.atm.utill;

import example.homework.atm.model.banknote.Banknote;
import example.homework.atm.model.banknote.Hundred;
import example.homework.atm.model.banknote.Thousand;
import lombok.NonNull;

import java.util.List;

public class OperationSum {
    private final int hundred;
    private final int thousand;
    private final int sum;

    public OperationSum(@NonNull int sum) {
        String sumString = (String.valueOf(sum));
        this.hundred = Integer.parseInt(sumString.substring(sumString.length() - 3, sumString.length() - 2));
        this.thousand = Integer.parseInt(sumString.substring(sumString.length() - 4, sumString.length() - 3));
        this.sum = sum;

    }

    public OperationSum(List<Banknote> banknoteList) {
        int allBanknoteSum = 0;
        int thousandCount = 0;
        int hundredCount = 0;
        for (Banknote banknote : banknoteList) {
            allBanknoteSum = allBanknoteSum + banknote.calculateSum();
            if (banknote instanceof Thousand) {
                thousandCount = ((Thousand) banknote).getCount();
            }
            if (banknote instanceof Hundred) {
                hundredCount = ((Hundred) banknote).getCount();
            }
        }
        this.hundred = hundredCount;
        this.thousand = thousandCount;
        this.sum = allBanknoteSum;
    }

    public int getSum() {
        return this.sum;
    }

    public int getThousand() {
        return this.thousand;
    }

    public int getHundred() {
        return this.hundred;
    }
}
