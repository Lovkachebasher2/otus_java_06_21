package example.homework.atm.model.banknote;

import lombok.Getter;

@Getter
public enum Denomination {
    HUNDRED(100),
    THOUSAND(1000);

    private final int value;

    Denomination(int value) {
        this.value = value;
    }
}
