package Examen;

import java.util.Stack;

public class ReversePolishNotationImpl implements ReversePolishNotation {

    public double calculate(String expression) {
        Stack<Double> operacion = new Stack<>();
        String[] partes = expression.split(" ");
        for (String parte : partes)
        {
            if (parte.equals("+") || parte.equals("-") || parte.equals("*") || parte.equals("/")) {
                double b = operacion.pop();
                double a = operacion.pop();
                double resultadoParcial = 0;
                switch (parte) {
                    case "+":
                        resultadoParcial = a + b;
                        break;
                    case "-":
                        resultadoParcial = a - b;
                        break;
                    case "*":
                        resultadoParcial = a * b;
                        break;
                    case "/":
                        resultadoParcial = a / b;
                        break;
                }
                operacion.push(resultadoParcial);
            }
            else {
                operacion.push(Double.parseDouble(parte));
            }
        }
        return operacion.pop();
    }
}