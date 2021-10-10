package example.homework.atm;

import example.homework.atm.balance.Balance;
import example.homework.atm.service.AtmOperationService;
import example.homework.atm.utill.Helper;
import example.homework.atm.utill.OperationSum;

public class Atm {
    private AtmOperationService atmServiceOperations;

    public Atm(AtmOperationService atmServiceOperations) {
        this.atmServiceOperations = atmServiceOperations;
    }

    public void addBankAccount(OperationSum operationSum) {
        atmServiceOperations.addBankAccount(operationSum);
        Helper.log("operation add bank account is successfully, balance now: " + Balance.getBalance());
    }

    public OperationSum outBankAccount(OperationSum operationSum) {
        OperationSum operationResponseSum = atmServiceOperations.outBankAccount(operationSum);
        Helper.log("you got it: " + operationSum.getThousand() + ".thousand, " + operationSum.getHundred() + ".hundred" + ". Balance now is: " + Balance.getBalance());
        return operationResponseSum;
    }
}
