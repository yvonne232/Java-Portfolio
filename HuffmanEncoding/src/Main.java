/**
 * Name: Ziyu(Yvonne) Lin
 * Lab 3
 * EN.605.202
 */

import java.io.*;
import java.util.*;

/**
 * This is the main method
 */
public class Main {

    /**
     * This is a method to generate frequency table that takes account of capitalization, tabs, and punctuation
     * @param inputFile sample file to calculate frequency table
     * @param outputFile save the results in the output file
     * @throws IOException
     */
    public static void generateFrequencyTable(String inputFile, String outputFile) throws IOException {
        Map<String, Integer> frequencyTable = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));

        int c; // Buffered reader returns an int
        while ((c = reader.read()) != -1) {
            String key = String.valueOf((char) c);
            // Save it to the hashmap
            frequencyTable.put(key, frequencyTable.getOrDefault(key, 0) + 1);
        }
        reader.close();


        // Write it to the output file
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        for (Map.Entry<String, Integer> entry: frequencyTable.entrySet()) {
            // Make the symbol readable
            String symbol = entry.getKey();
            if (symbol.equals(" ")) {
                symbol = "<space>";
            } else if (symbol.equals("\n")) {
                symbol = "<newline>";
            } else if (symbol.equals("\t")) {
                symbol = "<tab>";
            } else if (symbol.equals("\r")) {
                continue;
            }
            writer.write(symbol + " - " + entry.getValue());
            writer.newLine();
        }
        writer.close();

    }

    /**
     * This is the main method that reads frequency table, translate clear text into encoded file, and translate encoded file into decoded file
     * @param args - args[0] frequency file, args[1] clearText, args[2] Encoded, args[3] encoded output file, args[4] decoded output file
     */
    public static void main(String[] args) throws IOException {
        // Error checking: if there are less than or more than 5 files
        if (args.length != 5) {
            System.out.println("Args are not correct - Usage: <FreqTable> <ClearText> <Encoded> <EncodedOutputFile> <DecodedOutputFile>" );
            return;
        }

        // args[0] frequency file, args[1] clearText, args[2] Encoded
        String freqFile = args[0];
        String clearFile = args[1];
        String encodedFile = args[2];
        // Define output file location
        String encodedOutputFile = args[3];
        String decodedOutputFile = args[4];

        // Generate one frequency table based on the input file and save it
        generateFrequencyTable("input/ClearText.txt", "Input/GeneratedFrequencyTable.txt");

        String line;

        // Read frequency table
        Map<String, Integer> frequencyMap = new HashMap<>();
        BufferedReader freqReader = new BufferedReader(new FileReader(freqFile));
        while ((line = freqReader.readLine()) != null) {
            String[] tokens = line.split("\\s+"); // Split a string using white space
            frequencyMap.put(tokens[0], Integer.parseInt(tokens[2]));
        }
        freqReader.close();

        // Build huffman tree and encoder/decoder
        HuffmanNode root = HuffmanTree.buildHuffmanTree(frequencyMap);
        HuffmanEncoderDecoder encoderDecoder = new HuffmanEncoderDecoder(root);

        // Encoding
        BufferedReader cleatTextReader = new BufferedReader(new FileReader(clearFile));
        BufferedWriter cleatTextWriter = new BufferedWriter(new FileWriter(encodedOutputFile));
        while ((line = cleatTextReader.readLine()) != null) {
            // Change every character to upper case and ignore spaces
            String text = line.toUpperCase().replaceAll("\\s+", "");
            // Encode the text file
            String encoded = encoderDecoder.encode(text);
            // Write the encoded file
            cleatTextWriter.write(encoded);
            cleatTextWriter.newLine();
        }
        cleatTextReader.close();
        cleatTextWriter.close();

        // Decoding
        BufferedReader encodedReader = new BufferedReader(new FileReader(encodedFile));
        BufferedWriter encodedWriter = new BufferedWriter(new FileWriter(decodedOutputFile));
        while ((line = encodedReader.readLine()) != null) {
            // Decode the text file
            String decoded = encoderDecoder.decode(line);
            // Write the decoded file
            encodedWriter.write(decoded);
            encodedWriter.newLine();
        }
        encodedReader.close();
        encodedWriter.close();


        // Print the tree in preorder
        System.out.println("The tree in preorder is: ");
        HuffmanTree.printHuffmanTree(root);

        // Print the encoding map
        System.out.println();
        System.out.println("The code is: ");
        HuffmanTree.printEncodingMap(root);
    }
}