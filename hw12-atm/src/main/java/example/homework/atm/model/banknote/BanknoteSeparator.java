package example.homework.atm.model.banknote;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class BanknoteSeparator {

    public List<Banknote> sort(int sum) {
        List<Banknote> banknoteList = Collections.emptyList();
        int hundred = sum / Denomination.HUNDRED.getValue();
        int thousand = 0;
        if (hundred >= 10) {
            thousand = hundred / 10;
            hundred = hundred % 10;
        }
        banknoteList.addAll(makeBanknote(Denomination.HUNDRED, hundred));
        banknoteList.addAll(makeBanknote(Denomination.THOUSAND, thousand));
        return banknoteList;
    }

    private List<Banknote> makeBanknote(Denomination denomination, int count) {
        List<Banknote> banknoteList = Collections.emptyList();
        for (int i = 0; i < count; i++) {
            banknoteList.add(new Banknote(denomination));
        }
        return banknoteList;
    }


    public Map<Denomination, Integer> separateBanknote(List<Banknote> banknoteList) {
        Map<Denomination, Integer> denominationIntegerMap = new EnumMap<>(Denomination.class);
        int hundred = 0;
        int thousand = 0;
        for (var banknote : banknoteList) {
            if (banknote.getDenomination() == Denomination.HUNDRED) {
                hundred++;
            }
            if (banknote.getDenomination() == Denomination.THOUSAND) {
                thousand++;
            }
        }
        denominationIntegerMap.put(Denomination.HUNDRED, hundred);
        denominationIntegerMap.put(Denomination.THOUSAND, thousand);
        return denominationIntegerMap;
    }
}
