package example.homework.atm.service.utill;

import example.homework.atm.BoundKit;

public class Helper {

    public static BoundKit getRequestMoney(int totalSum) {
        return new Helper().calculation(totalSum);
    }

    /**
     * 990 total sum ->
     */


    private BoundKit calculation(int totalSum) {
        BoundKit boundKit = new BoundKit();
        boundKit.setTotal(totalSum);
//        requestMoney.setTen(Integer.par);
        return boundKit;
    }
}
