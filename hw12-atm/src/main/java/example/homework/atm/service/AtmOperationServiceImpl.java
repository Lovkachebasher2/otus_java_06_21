package example.homework.atm.service;

import example.homework.atm.balance.Balance;
import example.homework.atm.exception.AtmOperationException;
import example.homework.atm.utill.OperationSum;

public class AtmOperationServiceImpl implements AtmOperationService {

    @Override
    public void addBankAccount(OperationSum operationSum) {
        int balanceNow = Balance.getBalance();
        Balance.setBalance(balanceNow + operationSum.getSum());
    }

    @Override
    public OperationSum outBankAccount(OperationSum operationSum) {
        checkBalance(operationSum);
        int balanceNow = Balance.getBalance();
        Balance.setBalance(balanceNow - operationSum.getSum());
        return new OperationSum(operationSum.getSum());
    }

    private void checkBalance(OperationSum operationSum) {
        if (!balanceIsMoreSum(operationSum.getSum())) {
            throw new AtmOperationException("it is not enough/средств недостаточно");
        }
    }

    private boolean balanceIsMoreSum(int sum) {
        return Balance.getBalance() >= sum;
    }
}
