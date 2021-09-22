package example.homework.atm.service.atmoperations;

import example.homework.atm.service.utill.BoundKit;

public class AtmServiceOutPut implements AtmServiceOperations{
    @Override
    public void operationsWithBankAccount(BoundKit boundKit) {
        Balance.outBalance(boundKit.getSum());
    }
}
