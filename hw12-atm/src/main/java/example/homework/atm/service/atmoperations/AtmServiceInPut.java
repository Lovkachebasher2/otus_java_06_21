package example.homework.atm.service.atmoperations;

import example.homework.atm.service.utill.BoundKit;

public class AtmServiceInPut implements AtmServiceOperations{

    @Override
    public void operationsWithBankAccount(BoundKit boundKit) {
        Balance.addBalance(boundKit.getSum());
    }
}
