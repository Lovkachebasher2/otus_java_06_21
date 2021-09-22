package example.homework.atm.service.atmoperations;

import example.homework.atm.BoundKit;

public class AtmServiceInPut implements AtmServiceOperations{

    @Override
    public int chooseBankAccountBalance(BoundKit boundKit) {
        System.out.println("баланс пополнен");
        return 0;
    }
}
