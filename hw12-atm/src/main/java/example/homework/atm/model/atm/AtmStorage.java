package example.homework.atm.model.atm;

import example.homework.atm.model.banknote.Banknote;
import example.homework.atm.model.banknote.Denomination;
import example.homework.atm.model.exception.AtmOperationException;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class AtmStorage {

    private final Map<Denomination, Integer> atmStorageMoney;

    public AtmStorage(Map<Denomination, Integer> atmStorageMoney) {
        this.atmStorageMoney = atmStorageMoney;
    }

    public void depositMoney(List<Banknote> banknoteList) {
        Map<Denomination, Integer> separatedBanknotes = new EnumMap<>(Denomination.class);
        for (var banknote : banknoteList) {
            separatedBanknotes.merge(banknote.getDenomination(), 1, Integer::sum);
        }
    }

    public List<Banknote> withdrawMoney(int sum) {
        if (sum > getBalance()) {
            throw new AtmOperationException("under resourced");
        }
        return fillBanknoteList(sum);
    }

    private List<Banknote> fillBanknoteList(int sum) {
        List<Banknote> banknoteList = new ArrayList<>();
        for (Map.Entry<Denomination, Integer> banknoteEntry : atmStorageMoney.entrySet()) {
            if (sum != 0) {
                Denomination denomination = banknoteEntry.getKey();
                int count = banknoteEntry.getValue();
                int banknoteValue = denomination.getValue();
                int requiredBanknotesCount = Math.min(sum / banknoteValue, count);
                if (requiredBanknotesCount > 0) {
                    int remainingCount = count - requiredBanknotesCount;
                    while (requiredBanknotesCount != 0) {
                        sum -= banknoteValue;
                        banknoteList.add(new Banknote(denomination));
                        requiredBanknotesCount--;
                    }
                    atmStorageMoney.put(denomination, remainingCount);
                }
            }
        }
        return banknoteList;
    }

    public int getBalance() {
        return atmStorageMoney.entrySet()
                .stream()
                .map(e -> e.getKey().getValue() * e.getValue())
                .mapToInt(Integer::intValue).sum();
    }
}
