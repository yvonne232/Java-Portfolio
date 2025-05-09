/**
 * Name: Ziyu(Yvonne) Lin
 * Lab 2
 * EN.605.202
 */

import java.io.*;

/**
 * This is the main class that takes 2 args. arg[0] is the input file. arg[1] is the output file. In the main class, the program calls the NotationConverter class to convert prefix to postfix and postfix to prefix.
 */
public class Main {
    public static void main(String[] args) {
        String inputFile = args[0];
        String outputFile = args[1];

        /**
         * Try catch block helps error handling. We want to catch IO exception here.
         */
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String prefix = line.trim();
                String prefixToPostfix = NotationConverter.toPostfix(prefix);
                // Write the new prefix to postfix expression to the output file
                writer.write("Expression: " + prefix + "\n");
                writer.write("Prefix to postfix: " + prefixToPostfix + "\n");

                // If the prefix already has error, we are not going to process write postfix to prefix
                if (prefixToPostfix.startsWith("Invalid")) {
                    writer.write( "Postfix to prefix: N/A \n");
                } else {
                    writer.write("Postfix to prefix: " + NotationConverter.toPrefix(prefixToPostfix) + "\n");
                }

                // This is the Enhancement which uses the tree traversal.
                writer.write("-- Expression Tree Traversal (Enhancement) \n");
                // Build expression tree
                PrefixToPostfixConversion.Node root = PrefixToPostfixConversion.buildExpressionTree(prefix);
                if (root == null) {
                    writer.write("Invalid Expression.\n");
                } else {
                    String treePostfix = PrefixToPostfixConversion.getPostfix(root);
                    String treePrefix = PrefixToPostfixConversion.getPrefix(root);
                    writer.write("Postfix (from tree traversal): " + treePostfix + "\n");
                    writer.write("Prefix (from tree traversal): " + treePrefix + "\n");
                }

                writer.newLine();
                writer.write("-----------------------\n");


            }

        } catch (IOException e) // If IO exception occurs
        {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
