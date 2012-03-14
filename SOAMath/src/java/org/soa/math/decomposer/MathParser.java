/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.decomposer;

/**
 *
 * @author Basem
 */
public class MathParser
{

    private static final char[] validOperators =
    {
        '/', '*', '+', '-'
    };

    private MathParser()
    {
        /* Using a private contructor to prevent instantiation
        Using class as a simple static utility class
         */
    }

    private static int evaluate(String leftSide, char oper, String rightSide)
            throws IllegalArgumentException
    {
        System.out.println("Evaluating: " + leftSide + " (" + oper + ") " + rightSide);
        int total = 0;
        int leftResult = 0;
        int rightResult = 0;

        int operatorLoc = findOperatorLocation(leftSide);
        if (operatorLoc > 0 && operatorLoc < leftSide.length() - 1)
        {
            leftResult = evaluate(leftSide.substring(0, operatorLoc),
                    leftSide.charAt(operatorLoc),
                    leftSide.substring(operatorLoc + 1, leftSide.length()));
        } else
        {
            try
            {
                leftResult = Integer.parseInt(leftSide);
            } catch (Exception e)
            {
                throw new IllegalArgumentException("Invalid value found in portion of equation: "
                        + leftSide);
            }
        }

        operatorLoc = findOperatorLocation(rightSide);
        if (operatorLoc > 0 && operatorLoc < rightSide.length() - 1)
        {
            rightResult = evaluate(rightSide.substring(0, operatorLoc),
                    rightSide.charAt(operatorLoc),
                    rightSide.substring(operatorLoc + 1, rightSide.length()));
        } else
        {
            try
            {
                rightResult = Integer.parseInt(rightSide);
            } catch (Exception e)
            {
                throw new IllegalArgumentException("Invalid value found in portion of equation: "
                        + rightSide);
            }
        }

        System.out.println("Getting result of: " + leftResult + " " + oper + " " + rightResult);
        switch (oper)
        {
            case '/':
                total = leftResult / rightResult;
                break;
            case '*':
                total = leftResult * rightResult;
                break;
            case '+':
                total = leftResult + rightResult;
                break;
            case '-':
                total = leftResult - rightResult;
                break;
            default:
                throw new IllegalArgumentException("Unknown operator.");
        }
        System.out.println("Returning a result of: " + total);
        return total;
    }

    private static int findOperatorLocation(String string)
    {
        int index = -1;
        for (int i = validOperators.length - 1; i >= 0; i--)
        {
            index = string.indexOf(validOperators[i]);
            if (index >= 0)
            {
                return index;
            }
        }
        return index;
    }

    public static int processEquation(String equation)
            throws IllegalArgumentException
    {
        return evaluate(equation, '+', "0");
    }

    public int parseExpression(String expression)
    {

            int result = MathParser.processEquation(expression);
            
            return result;
    }
    
    public static void main(String[] args)
    {
        System.out.println(MathParser.processEquation("(2+)3*7/2-1"));
    }
}
