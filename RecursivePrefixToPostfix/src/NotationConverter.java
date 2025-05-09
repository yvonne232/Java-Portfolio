/**
 * Name: Ziyu(Yvonne) Lin
 * Lab 2
 * EN.605.202
 */

/**
 * This is the NotationConverter class, which is used for converting postfix to prefix and prefix to postfix.
 */
public class NotationConverter {

    /**
     * This is the Index class, which helps define for forward tracking or backward tracking.
     */
    private static class Index {
        // If it is prefix to postfix alone, define the index like private static int index = 0. Now I added a new index class for forward or backward tracking.
        int value;
    }

    /**
     * This function is to verify whether the character is an operator
     * @param c - one character in the expression
     * @return true if the character is an operator
     */
    private static boolean isOperator(char c)
    {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '$';
    }

    /**
     * This function is to check whether the prefix expression is valid
     * @param input - prefix expression
     * @return true if the prefix expression is a valid prefix
     */
    public static boolean isValidPrefix(String input) {
        int operatorNum = 0;
        int operandNum = 0;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (isOperator(c)) {
                operatorNum++;
            } else if (Character.isLetterOrDigit(c)) {
                operandNum++;
            } else {
                return false; // It finds an invalid expression
            }

            // At every point, a valid expression will have 1 more operand than operator
            if (operandNum > operatorNum + 1) {
                return false;
            }
        }

        // A valid expression will have 1 more operand than operator
        return operandNum == operatorNum + 1;
    }

    /**
     * This function is to check whether the postfix expression is valid
     * @param input - postfix expression
     * @return true if the postfix expression is valid
     */
    public static boolean isValidPostfix(String input) {
        int operatorNum = 0;
        int operandNum = 0;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (isOperator(c)) {
                operatorNum++;
                // At least 2 operands are needed before an operator is applied
                if (operandNum < 2) {
                    return false;
                }
                // One operator will combine 2 operands into 1
                operandNum--;
            } else if (Character.isLetterOrDigit(c)) {
                operandNum++;
            } else {
                return false; // It finds an invalid expression
            }
        }
        // At last, it will have 1 operand
        return operandNum == 1;
    }

    /**
     * This function is a helper function for recursion. It builds the recursion logic for converting prefix to postfix.
     * @param prefix - prefix expression
     * @param index - starting point of the expression for the recursion. If it is prefix to postfix, it starts from index = 0
     * @return postfix expression
     */
    private static String toPostfixRecursion(String prefix, Index index) {
        // Get current character and move forward
        char c = prefix.charAt(index.value++);

        // Base case: if it is an operand, return it
        // Since operands are the leaf nodes in the recursion case, and operators are the nodes that have 2 child leaf nodes
        // We stop when it is an operand, because there are no more subexpressions
        if (Character.isLetterOrDigit(c)) {
            return Character.toString(c);
        }

        // If it is an operator, recursively process 2 operands
        String op1 = toPostfixRecursion(prefix, index); // This is left operand
        String op2 = toPostfixRecursion(prefix, index); // This is right operand

        // return postfix expression
        return op1 + op2 + c;
    }

    /**
     * This function is the driver function that checks whether the prefix is a valid prefix. If it is valid, then call the toPostfixRecursion(prefix, index) function.
     * @param prefix - prefix expression
     * @return postfix expression
     */
    public static String toPostfix(String prefix) {
        if (!isValidPrefix(prefix)) {
            return "Invalid Expression"; // Return invalid expression message
        }
        // index = 0
        Index index = new Index();
        index.value = 0;
        return toPostfixRecursion(prefix, index);
    }

    /**
     * This function is a recursion helper function for converting postfix to prefix
     * @param postfix - postfix expression
     * @param index - starting point of the expression for the recursion. If it is postfix to prefix, it starts from the end of the postfix expression
     * @return prefix expression
     */
    public static String toPrefixRecursion(String postfix, Index index) {
        // postfix to prefix: read from last character
        // Since operator comes from last, we need to find the operator first and then recursively build the operands
        char c = postfix.charAt(index.value);
        index.value--;

        // Base case: if it is an operand, return it
        // Since operands are the leaf nodes in the recursion case, and operators are the nodes that have 2 child leaf nodes
        // We stop when it is an operand, because there are no more subexpressions
        if (Character.isLetterOrDigit(c)) {
            return Character.toString(c);
        }

        // If it is an operator, recursively process 2 operands
        String op1 = toPrefixRecursion(postfix, index); // This is right operand
        String op2 = toPrefixRecursion(postfix, index); // This is left operand

        return c + op2 + op1;
    }

    /**
     * This function is a driver function that checks whether the postfix is a valid postfix expression. If it is valid, then call the toPrefixRecursion(postfix, index) function to convert it to prefix.
     * @param postfix postfix expression
     * @return prefix expression
     */
    public static String toPrefix(String postfix) {
        // postfix to prefix: read from last character
        // Since operator comes from last, we need to find the operator first and then recursively build the operands
        if (!isValidPostfix(postfix)) {
            return "Invalid Expression"; // Return invalid expression message
        }
        Index index = new Index();
        index.value = postfix.length() - 1;
        return toPrefixRecursion(postfix, index);
    }
}
