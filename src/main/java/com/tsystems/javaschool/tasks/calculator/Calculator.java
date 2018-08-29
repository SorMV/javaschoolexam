package com.tsystems.javaschool.tasks.calculator;

public class Calculator {

    /**
     * Evaluate statement represented as string.
     *
     * @param statement mathematical statement containing digits, '.' (dot) as decimal mark,
     *                  parentheses, operations signs '+', '-', '*', '/'<br>
     *                  Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
     * @return string value containing result of evaluation or null if statement is invalid
     */
    public String evaluate(String statement) {
        Calculator c = new CalculatorImpl();

        return c.evaluate(statement);
    }

    public static void main(String[] args) {
    Calculator c = new Calculator();
System.out.println(c.evaluate("2+3")); // Result: 151
System.out.println(c.evaluate("4-6")); // Result: 29
System.out.println(c.evaluate("2*3")); // Result: null
    }

}
