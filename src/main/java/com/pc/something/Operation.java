package com.pc.something;

@FunctionalInterface
interface DoubleBinaryOperator {
    double apply(double x,double y);
}

public enum  Operation {
    PLUS ("+",(x,y)->x+y),
    MINUS ("-",(x,y)->x-y),
    TIMES ("*",(x,y)->x*y),
    DIVIDE ("/",(x,y)->x/y),;

    private final String ope;
    private final DoubleBinaryOperator doubleBinaryOperator;

    Operation(String ope,DoubleBinaryOperator operator)  {
        this.ope = ope;
        this.doubleBinaryOperator = operator;
    }

    public double apply(double x, double y) {
        return doubleBinaryOperator.apply(x,y);
    }

    public static void main(String[] args) {
        double x = Operation.PLUS.apply(10,11);
//        System.out.println(x);
    }

}
