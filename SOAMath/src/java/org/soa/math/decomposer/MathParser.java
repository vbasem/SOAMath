/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.decomposer;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        /*
         * Using a private contructor to prevent instantiation Using class as a
         * simple static utility class
         */
    }

    private static String evaluate(String leftSide, char oper, String rightSide)
            throws IllegalArgumentException, InterruptedException, ExecutionException
    {
        System.out.println("Evaluating: " + leftSide + " (" + oper + ") " + rightSide);
        Future<String> total = null;

        String leftResult = null;
        String rightResult = null;

        int operatorLoc = findOperatorLocation(leftSide);
        if (operatorLoc > 0 && operatorLoc < leftSide.length() - 1)
        {
            leftResult = evaluate(leftSide.substring(0, operatorLoc),
                    leftSide.charAt(operatorLoc),
                    leftSide.substring(operatorLoc + 1, leftSide.length()));
        } else
        {
            leftResult = leftSide;
        }

        operatorLoc = findOperatorLocation(rightSide);

        if (operatorLoc > 0 && operatorLoc < rightSide.length() - 1)
        {
            rightResult = evaluate(rightSide.substring(0, operatorLoc),
                    rightSide.charAt(operatorLoc),
                    rightSide.substring(operatorLoc + 1, rightSide.length()));
        } else
        {
            rightResult = rightSide;
        }

        System.out.println("Getting result of: " + leftResult + " " + oper + " " + rightResult);

        switch (oper)
        {
            case '/':
                total = PrimitiveMath.divide(leftResult, rightResult);
                break;
            case '*':
                total = PrimitiveMath.multiply(leftResult, rightResult);
                break;
            case '+':
                total = PrimitiveMath.add(leftResult, rightResult);
                break;
            case '-':
                total = PrimitiveMath.substract(leftResult, rightResult);
                break;
            default:
                throw new IllegalArgumentException("Unknown operator.");
        }

        String result = total.get();
        System.out.println("Returning a result of: " + result);

        return result;
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

    public static String processEquation(String equation)
    {
        String result = null;
        try
        {
            result = evaluate(equation, '+', "0");
        } catch (IllegalArgumentException ex)
        {
            Logger.getLogger(MathParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex)
        {
            Logger.getLogger(MathParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex)
        {
            Logger.getLogger(MathParser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public String parseExpression(String expression)
    {

        String result = MathParser.processEquation(expression);

        return result;
    }

    public static void main(String[] args)
    {
        System.out.println(MathParser.processEquation("2+3*7/2-1"));
        System.exit(0);
    }
}
