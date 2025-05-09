/**
 * Name: Ziyu(Yvonne) Lin
 * Lab1
 * EN.605.202
 */

import java.io.*;

/**
 * This class is for convert notations
 */
public class NotationConverter
{
    /**
     * This is a helper function to check whether the character is an operator or not
     * @param c one char in the prefix expression
     * @return True/false. If it is an operator, return true, otherwise return false.
     */
    public static boolean isOperator(char c)
    {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '$';
    }

    /**
     * This method converts prefix expression to postfix expression
     * @param prefix prefix expression
     * @return postfix expression
     */
    public static String toPostfix(String prefix)
    {
        Stack<String> stack = new Stack<>();
        try{
            // Read the prefix expression in reverse order
            for (int i = prefix.length()-1; i>=0; i--)
            {
                char c = prefix.charAt(i);

                // If it has empty spaces in the middle, skip it
                if (c == ' ') {
                    continue;
                }

                if (Character.isLetterOrDigit(c)) {
                    // if it is an operand, push it to the stack
                    stack.push(Character.toString(c));
                } else if (isOperator(c)) {
                    // If it is an operator, have the postfix string, which is op1 + op2 + operator

                    // Error checking: if there are less than 2 operands in the stack
                    if (stack.size() < 2)
                    {
                        throw new IllegalArgumentException("Invalid prefix expression - less than 2 operands in the stack for operators: " + prefix );
                    }

                    String op1 = stack.pop();
                    String op2 = stack.pop();
                    String postfix = op1 + op2 + c;
                    stack.push(postfix);
                } else // else, invalid character
                {
                    throw new IllegalArgumentException("Invalid prefix expression - invalid character in the expression: " + prefix);
                }
            }

            // Error checking: if the final stack size != 1
            if (stack.size() != 1)
            {
                throw new IllegalArgumentException("Invalid prefix expression - more than 1 element in the stack at the end: " + prefix);
            }
            return stack.peek();

        } catch (Exception e) {
            return e.getMessage();
        }

    }

    /**
     * This method is to convert postfix expression to prefix expression
     * @param postfix postfix string
     * @return String prefix
     */
    public static String toPrefix(String postfix)
    {
        Stack<String> stack = new Stack<>();
        // try catch block helps return the error message and have it in the output
        try
        {
            // Read the postfix expression from left to right
            for (int i = 0; i < postfix.length(); i++)
            {
                char c = postfix.charAt(i);

                // If it has empty spaces in the middle, skip it
                if (c == ' ') {
                    continue;
                }

                if (Character.isLetterOrDigit(c)) {
                    // if it is an operand, push it to the stack
                    stack.push(Character.toString(c));
                } else if (isOperator(c)) {
                    // Error checking: if there are less than 2 operands in the stack
                    if (stack.size() < 2) {
                        throw new IllegalArgumentException("Invalid prefix expression - less than 2 operands in the stack for operators: " + postfix);
                    }
                    // This part is different than the prefix to postfix one
                    String op2 = stack.pop();
                    String op1 = stack.pop();
                    String prefix = c + op1 + op2; // combine the operator with 2 operands in the stack
                    // Push the intermediate prefix expression back to the stack
                    stack.push(prefix);
                } else {
                    throw new IllegalArgumentException("Invalid prefix expression - invalid character in the expression: " + postfix);
                }
            }

            // Error checking: if the final stack size != 1
            if (stack.size() != 1)
            {
                throw new IllegalArgumentException("Invalid prefix expression - more than 1 element in the stack at the end: " + postfix);
            }

            return stack.peek();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
