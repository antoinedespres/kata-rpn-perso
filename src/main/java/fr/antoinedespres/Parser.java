package fr.antoinedespres;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Parser {
    // TODO add safety (bad format, illegal operators)
    public static double evaluate(String sequence) {
        Stack<Double> stack = new Stack<>();
        List<String> tokens = Arrays.stream(sequence.split(" ")).toList();
        for(String t : tokens) {
            if(isNumeric(t)) {
                stack.push(Double.parseDouble(t));
            }
            else {
               stack.push(compute(stack.pop(), stack.pop(), t));
            }
        }
        return stack.pop();
    }

    private static boolean isNumeric(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    private static double compute(double nb1, double nb2, String op) {
        return switch (op) {
            case "+" -> nb2 + nb1;
            case "-" -> nb2 - nb1; // If "1 2 -" we should do 1-2 and not 2-1
            case "*" -> nb2 * nb1;
            case "/" -> {
                if (nb1 == 0) throw new ArithmeticException();
                yield nb2 / nb1;
            }
            default -> 1.;
        };
    }
}
