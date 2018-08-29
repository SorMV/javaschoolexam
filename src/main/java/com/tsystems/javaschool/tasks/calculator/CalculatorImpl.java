package com.tsystems.javaschool.tasks.calculator;

/**
 *
 *
 */

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.Collections;
import java.util.Scanner;
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
        int flag = 0;
        int functionFlag=0;

        input = input.replace("(-", "(0-");
        if (input.charAt(0) == '-') {
            input = "0" + input;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(input,
                FUNCTIONS + "()", true);
        while (stringTokenizer.hasMoreTokens()) {
            String token = stringTokenizer.nextToken();
            if(functionFlag>=2) {
                throw new IllegalArgumentException();
            }
            if (isOpenBracket(token)) {
                flag=flag+1;
                stack.push(token);
            } else if (isCloseBracket(token)) {
                flag=flag-1;
                if(flag!=0) throw new IllegalArgumentException();
                while (!stack.empty()
                        && !isOpenBracket(stack.lastElement())) {
                    polishStack.push(stack.pop());
                }
                stack.pop();
            } else if (isNumber(token)) {
                if(functionFlag!=0) {
                    functionFlag--;
                }
                polishStack.push(token);
            } else if (isFunction(token)) {
                functionFlag++;
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
        stack.clear();
        double c=0;
        String func="0";
        while (polishStack.size()!=1) {
            boolean flag=true;
            double a;
            double b;

            a = new Double(polishStack.pop());
            if(!isNumber(polishStack.lastElement())){
                func=polishStack.pop();
                b=a;
                a=c;
                flag=false;
            }
            else {
                b = new Double(polishStack.pop());
            }

               if(polishStack.size()>=1&&isFunction(polishStack.lastElement())&&flag) {
                    func = polishStack.pop();
                }
                else {
                   if (flag) {
                       c = a;
                       a = b;
                       b = new Double(polishStack.pop());
                       func = polishStack.pop();
                   }
               }

            switch (func) {
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
        if(input!=null&&input!="") {
            try {
                toPolish(input);
                String answer = calculate();
                int indx = answer.indexOf(".") + 1;
                if ((answer.charAt(indx) == '0') && (answer.length() == indx + 1)) {
                    answer = answer.substring(0, indx - 1);
                    return answer;
                }
                BigDecimal bd = new BigDecimal(answer);
                bd = bd.setScale(4, RoundingMode.HALF_UP);
                return Double.toString(bd.doubleValue());
            } catch (ParseException | IllegalArgumentException e) {
                return null;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String input = new String("(12*(5-1)");
        CalculatorImpl cl = new CalculatorImpl();
        System.out.println(cl.evaluate("2+3")); // Result: 151
        System.out.println(cl.evaluate("(1+38)*4-5"));
        System.out.println(cl.evaluate("10/2-7+3*4"));
        System.out.println(cl.evaluate(input));
        System.out.println(cl.evaluate("10/(2-7++3)*4"));



    }
}
