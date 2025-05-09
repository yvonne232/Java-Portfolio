/**
 * Name: Ziyu(Yvonne) Lin
 * Lab 2
 * EN.605.202
 */

import java.io.*;

/**
 * This is the main function
 */
public class Main {
    /**
     * This is the main function
     * @param args args[0] is the input file, args[1] is the output file
     */
    public static void main(String[] args)
    {
        String inputFile = args[0];
        String outputFile = args[1];

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile)))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                if (line.trim().isEmpty())
                {
                    continue;
                }
                String prefix = line.trim();
                String prefixToPostfix = NotationConverter.toPostfix(prefix);
                String postfixToPrefix =  NotationConverter.toPrefix(prefixToPostfix);
                // Write the new prefix to postfix expression to the output file
                writer.write("Expression: " + prefix + "\n");
                writer.write("Prefix to postfix: " + prefixToPostfix + "\n");
                // If the prefix already has error, we are not going to process write postfix to prefix
                if (prefixToPostfix.startsWith("Invalid")) {
                    writer.write( "Postfix to prefix: N/A \n");
                    writer.newLine();
                } else {
                    writer.write("Postfix to prefix: " + postfixToPrefix + "\n");
                    writer.newLine();
                }
            }
        } catch (IOException e) // If IO exception occurs
        {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
