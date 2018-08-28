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
        // TODO: Implement the logic here
        return "";
    }

    public static void main(String[] args) {
    Calculator c = new CalculatorImpl();
System.out.println(c.evaluate("(1+38)*4-5")); // Result: 151
System.out.println(c.evaluate("7*6/2+8")); // Result: 29
System.out.println(c.evaluate("-12)1//(")); // Result: null
    }

}
