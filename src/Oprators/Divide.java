package Oprators;

public class Divide implements OperatorMaker {
    private final double firstNumber;
    private final double secondNumber;

    public Divide(double firstNumber, double secondNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }

    @Override
    public double performAction() {
        return firstNumber / secondNumber;
    }

    @Override
    public double result(){
        return performAction();
    }
}
