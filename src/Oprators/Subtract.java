package Oprators;

public class Subtract implements OperatorMaker {
    private final double firstNumber;
    private final double secondNumber;

    public Subtract(double firstNumber, double secondNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }

    @Override
    public double performAction() {
        return firstNumber - secondNumber;
    }

    @Override
    public double result() {
        return performAction();
    }


}
