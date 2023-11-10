package fr.antoinedespres;

public class Main {
    public static void main(String[] args) {
        Parser p = new Parser();
        double res = p.evaluate("1 2 * 4 + 2 /");
        System.out.println("Result: " + res);
    }
}