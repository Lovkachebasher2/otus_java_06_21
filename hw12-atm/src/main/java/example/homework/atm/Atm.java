package example.homework.atm;

import example.homework.atm.service.atmoperations.AtmServiceOperations;

public class Atm {
    private AtmServiceOperations atmServiceOperations;
    private  int sum;

    public Atm(int sum) {
        this.sum = sum;
    }

    public void setAtmServiceOperations(AtmServiceOperations atmServiceOperations) {
        this.atmServiceOperations = atmServiceOperations;
    }

    public void executeOperations(BoundKit boundKit) {
        sum = this.atmServiceOperations.chooseBankAccountBalance(boundKit);
        System.out.println("sum now: " + sum);
    }
}
