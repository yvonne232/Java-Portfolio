/**
 * Name: Ziyu(Yvonne) Lin
 * Lab 2
 * EN.605.202
 */

/**
 * This is a class for prefix to postfix conversion. This is one enhancement for the lab. After listening to the office hour, I decide to redo it using the tree-based way.
 * In the office hour, Scott said we could build an expression tree, and different ways of traversal gives us postfix expression or prefix expression. I am trying this way in this class.
 */
public class PrefixToPostfixConversion {

    /**
     * Expression tree node
     */
    static class Node {
        char c;
        Node left, right;

        Node(char c) {
            this.c = c; // Node characteristics
            this.left = this.right = null;
        }
    }

    /**
     * This is the Index class, which helps define for forward tracking or backward tracking.
     */
    private static class Index {
        // If it is prefix to postfix alone, define the index like private static int index = 0. Now I added a new index class for forward or backward tracking.
        int value;
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
     * This function is to verify whether the character is an operator
     * @param c - one character in the expression
     * @return true if the character is an operator
     */
    private static boolean isOperator(char c)
    {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '$';
    }

    /**
     * This function is a helper function to build an expression tree
     * @param prefix - prefix expression
     * @param index - starting index
     * @return Node - root node of the tree
     */
    private static Node buildExpressionTreeHelper(String prefix, Index index) {
        if (index.value >= prefix.length()) {
            return null;
        } // This means it has finished traversal. Base case of the recursion

        char c = prefix.charAt(index.value++); // Get the current character and then increment the index (post-increment)
        // So, next recursive call will move to the next char
        Node node = new Node(c); // build a c node

        if (isOperator(c)) {
            node.left = buildExpressionTreeHelper(prefix, index); // build left subtree
            node.right = buildExpressionTreeHelper(prefix, index); // build right subtree
        }

        return node; // return root node
    }

    /**
     * This function is a driver function to build expression tree that calls the helper recursion function
     * @param prefix - prefix expression
     * @return node - root node of the tree
     */
    public static Node buildExpressionTree(String prefix) {
        if (!isValidPrefix(prefix)) {
            return null;
        } // If it is invalid expression
        // index = 0
        Index index = new Index();
        index.value = 0;

        return buildExpressionTreeHelper(prefix, index);
    }

    /**
     * This is a class that is to build postfix expression from the tree
     * @param root root node of the expression tree
     * @return  postfix expression
     */
    public static String getPostfix(Node root) {
        if (root == null) {
            return ""; // It means we have printed everything
        }
        // Recursively call the function to print left node + right node + root
        return getPostfix(root.left) + getPostfix(root.right) + root.c;
    }

    /**
     * This is a class that is to build prefix expression from the tree
     * @param root root node of the expression tree
     * @return prefix expression
     */
    public static String getPrefix(Node root) {
        if (root == null) {
            return "";
        }
        // Recursively call the function to print root + left node + right node
        return root.c + getPrefix(root.left) + getPrefix(root.right);
    }

}
