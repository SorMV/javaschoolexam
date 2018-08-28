package com.tsystems.javaschool.tasks.calculator;

/**
 *
 *
 */

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;


public class CalculatorImpl extends Calculator {
    private final String FUNCTIONS = "+-*/";
    private Stack<String> stack = new Stack<>();
    private Stack<String> polishStack = new Stack<>();

    private boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private boolean isOpenBracket(String token) {
        return token.equals("(");
    }

    private boolean isCloseBracket(String token) {
        return token.equals(")");
    }

    private boolean isFunction(String token) {
        return FUNCTIONS.contains(token);
    }

    private byte getPriority(String token) {
        if (token.equals("+") || token.equals("-")) {
            return 1;
        }
        return 2;
    }

    private void toPolish(String input) throws ParseException, IllegalArgumentException {
        stack.clear();
        polishStack.clear();
        boolean flag = false;

        input = input.replace("(-", "(0-");
        if (input.charAt(0) == '-') {
            input = "0" + input;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(input,
                FUNCTIONS + "()", true);
        while (stringTokenizer.hasMoreTokens()) {
            String token = stringTokenizer.nextToken();
            if (isOpenBracket(token)) {
                flag=true;
                stack.push(token);
            } else if (isCloseBracket(token)) {
                if(!flag) throw new IllegalArgumentException();
                while (!stack.empty()
                        && !isOpenBracket(stack.lastElement())) {
                    polishStack.push(stack.pop());
                    flag=false;
                }
                stack.pop();
            } else if (isNumber(token)) {
                polishStack.push(token);
            } else if (isFunction(token)) {
                while (!stack.empty()
                        && isFunction(stack.lastElement())
                        && getPriority(token) <= getPriority(stack.lastElement())) {
                    polishStack.push(stack.pop());
                }
                stack.push(token);
            }
            else throw new IllegalArgumentException();
        }
        while (!stack.empty()) {
            polishStack.push(stack.pop());
        }
        Collections.reverse(polishStack);
    }

    private String calculate() throws NumberFormatException {

        double result=0;
        while (polishStack.size()!=1) {
            double a = new Double(polishStack.pop());
            double b = new Double(polishStack.pop());
            switch (polishStack.pop()) {
                case "+":
                    result = a+b;
                    break;
                case "-":
                    result = a-b;
                    break;
                case "*":
                    result =a*b;
                    break;
                case "/":
                    result = a/b;
                    break;
            }
            polishStack.push(Double.toString(result));
        }
        return polishStack.peek();
    }

    public String evaluate(String input) {
        try {
            toPolish(input);
            String answer = calculate();
            BigDecimal bd = new BigDecimal(answer);
            bd = bd.setScale(4, RoundingMode.HALF_UP);
            return Double.toString(bd.doubleValue());
        } catch (ParseException | IllegalArgumentException e) {
            return null;
        }
    }

//    public static void main(String[] args) {
//        String input = new String("(10+10.5345+40.48974536)/10");
//        CalculatorImpl cl = new CalculatorImpl();
//        System.out.println(cl.evaluate(input));
//    }
}
