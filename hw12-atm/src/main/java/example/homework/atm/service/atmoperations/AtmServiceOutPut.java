package example.homework.atm.service.atmoperations;

import example.homework.atm.BoundKit;

public class AtmServiceOutPut implements AtmServiceOperations{
    @Override
    public int chooseBankAccountBalance(BoundKit boundKit) {
        System.out.println("со счета списано");
        return 0;
    }
}
