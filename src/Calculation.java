import Oprators.Divide;
import Oprators.Multiply;
import Oprators.Subtract;
import Oprators.Sum;

public class Calculation {
    private static int position;
    private static String expression;

    private static double result;

    public Calculation(String problem) {
        expression = problem;
        try {
            position = 0; // Initialize the position to 0
            result = evaluateExpression();
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid expression. Please try again.");
        }
    }

    private static double evaluateExpression() {
        double value = evaluateTerm();
        while (position < expression.length()) {
            char operator = expression.charAt(position);
            if (operator == '+' || operator == '-') {
                position++;
                double termValue = evaluateTerm();
                if (operator == '+') {
                    value = new Sum(value, termValue).result();
                } else {
                    value = new Subtract(value, termValue).result();
                }
            } else {
                break;
            }
        }
        return value;
    }

    private static double evaluateTerm() {
        double value = evaluateFactor();
        while (position < expression.length()) {
            char operator = expression.charAt(position);
            if (operator == '*' || operator == '/') {
                position++;
                double factorValue = evaluateFactor();
                if (operator == '*') {
                    value = new Multiply(value, factorValue).result();
                } else {
                    if (factorValue == 0) {
                        throw new IllegalArgumentException("Division by zero");
                    }
                    value = new Divide(value, factorValue).result();
                }
            } else {
                break;
            }
        }
        return value;
    }

    private static double evaluateFactor() {
        if (position >= expression.length()) {
            throw new IllegalArgumentException("Invalid expression");
        }

        char ch = expression.charAt(position);
        if (Character.isDigit(ch) || ch == '-') {
            boolean isNegative = false;
            if (ch == '-') {
                isNegative = true;
                position++;
            }
            StringBuilder number = new StringBuilder();
            while (position < expression.length() && (Character.isDigit(expression.charAt(position)) || expression.charAt(position) == '.')) {
                number.append(expression.charAt(position));
                position++;
            }
            double value = Double.parseDouble(number.toString());
            if (isNegative) {
                value = -value;
            }
            return value;
        } else if (ch == '(') {
            position++;
            double value = evaluateExpression();
            if (position >= expression.length() || expression.charAt(position) != ')') {
                throw new IllegalArgumentException("Invalid expression");
            }
            position++;
            return value;
        } else {
            throw new IllegalArgumentException("Invalid expression");
        }
    }

    public double getResult() {
        return result;
    }
}
