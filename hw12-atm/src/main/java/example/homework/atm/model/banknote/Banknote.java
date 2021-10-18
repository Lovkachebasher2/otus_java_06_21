package example.homework.atm.model.banknote;

import lombok.Getter;

@Getter
public class Banknote {
    private final Denomination denomination;

    public Banknote(Denomination denomination) {
        this.denomination = denomination;
    }
}
