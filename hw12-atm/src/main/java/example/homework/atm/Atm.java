package example.homework.atm;

import example.homework.atm.service.atmoperations.AtmServiceOperations;
import example.homework.atm.service.atmoperations.Balance;
import example.homework.atm.service.utill.BoundKit;

public class Atm {
    private AtmServiceOperations atmServiceOperations;

    public void setAtmServiceOperations(AtmServiceOperations atmServiceOperations) {
        this.atmServiceOperations = atmServiceOperations;
    }

    public void executeOperations(BoundKit boundKit) {
        atmServiceOperations.operationsWithBankAccount(boundKit);
        System.out.println("operation is successfully, balance now is: " + Balance.getBalance());
    }
}
