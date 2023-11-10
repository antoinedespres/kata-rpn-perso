package fr.antoinedespres;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;

public class ParserTest {

    // TODO add safety tests (bad format, illegal operators)

    @Test
    public void test_addition_returns_correct_result() {
        String expression = "1 2 +";
        double expected = 3.;

        Assertions.assertEquals(expected, Parser.evaluate(expression));
    }

    @Test
    public void test_subtraction_returns_correct_result() {
        String expression = "1 2 -"; // should do 1 - 2 = -1
        double expected = -1.;

        Assertions.assertEquals(expected, Parser.evaluate(expression));
    }

    @Test
    public void test_product_returns_correct_result() {
        String expression = "9 8 * 7 *";
        double expected = 504.;

        Assertions.assertEquals(expected, Parser.evaluate(expression));
    }

    @Test
    public void test_division_by_zero_returns_ArithmeticException() {
        String expression = "10 0 /";

        Assertions.assertThrows(ArithmeticException.class, () -> Parser.evaluate(expression));
    }

    @Test
    public void test_division_returns_not_truncated() {
        String expression = "8 3 /";
        double expected = 2.67;

        DecimalFormat df = new DecimalFormat("#.##");
        double result = Parser.evaluate(expression);
        double rounded = Double.parseDouble(df.format(result).replace(",", ".")); // these French...

        Assertions.assertEquals(expected, rounded);
    }

    @Test
    public void test_complex_sequence_returns_correct_result() {
        String expression = "9 8 - 7 + 50 * 25 /";
        double expected = 16.;

        Assertions.assertEquals(expected, Parser.evaluate(expression));
    }
}
