package example.homework.atm.service;

import example.homework.atm.utill.OperationSum;

public interface AtmOperationService {
    void addBankAccount(OperationSum operationSum);

    OperationSum outBankAccount(OperationSum operationSum);
}
