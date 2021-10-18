package example.homework.atm.model.atm;

import example.homework.atm.model.banknote.Banknote;
import example.homework.atm.model.banknote.BanknoteSeparator;
import example.homework.atm.model.banknote.Denomination;
import example.homework.atm.model.exception.AtmOperationException;

import java.util.List;
import java.util.Map;

public class AtmStorage {

    private final Map<Denomination, Integer> atmStorageMoney;
    private final BanknoteSeparator banknoteSeparator;

    public AtmStorage(Map<Denomination, Integer> atmStorageMoney) {
        this.atmStorageMoney = atmStorageMoney;
        this.banknoteSeparator = new BanknoteSeparator();
    }

    public void depositMoney(List<Banknote> banknoteList) {
        for (Banknote banknote : banknoteList) {
            atmStorageMoney.merge(banknote.getDenomination(), 1, Integer::sum);
        }
    }

    public void withdrawMoney(List<Banknote> banknoteList) {
        Map<Denomination, Integer> denominationIntegerMap = banknoteSeparator.separateBanknote(banknoteList);
        isContain(denominationIntegerMap);
        for (var denomination : denominationIntegerMap.keySet()) {
            atmStorageMoney.put(denomination, atmStorageMoney.get(denomination) - denominationIntegerMap.get(denomination));
        }
    }

    private void isContain(Map<Denomination, Integer> denominationIntegerMap) {
        for (var denomination : denominationIntegerMap.keySet()) {
            if (atmStorageMoney.get(denomination) < denominationIntegerMap.get(denomination)) {
                throw new AtmOperationException("under resourced");
            }
        }
    }

    public int getBalance() {
        return atmStorageMoney.entrySet()
                .stream()
                .map(e -> e.getKey().getValue() * e.getValue())
                .mapToInt(Integer::intValue).sum();
    }
}
