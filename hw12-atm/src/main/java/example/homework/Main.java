package example.homework;

import example.homework.atm.Atm;
import example.homework.atm.service.atmoperations.AtmServiceInPut;
import example.homework.atm.service.atmoperations.AtmServiceOutPut;
import example.homework.atm.service.utill.BoundKit;

public class Main {
    public static void main(String[] args) {
        Atm atm = new Atm();

        atm.setAtmServiceOperations(new AtmServiceInPut());
        atm.executeOperations(new BoundKit());

        atm.setAtmServiceOperations(new AtmServiceOutPut());
        atm.executeOperations(new BoundKit());
    }
}
