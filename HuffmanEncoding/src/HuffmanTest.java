/**
 * Name: Ziyu(Yvonne) Lin
 * Lab 3
 * EN.605.202
 */

import org.junit.jupiter.api.Test;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;


/**
 * This is a JUnit Test for debugging
 */
public class HuffmanTest {

    /**
     * This is a junit test for testing hello world encoded strings, and decoded part
     */
    @Test
    public void testHuffman() throws IOException {

        // read frequency table
        File freqFile = new File("input/FreqTable.txt");
        // Read frequency table
        String line;
        Map<String, Integer> frequencyMap = new HashMap<>();
        BufferedReader freqReader = new BufferedReader(new FileReader(freqFile));
        while ((line = freqReader.readLine()) != null) {
            String[] tokens = line.split("\\s+"); // Split a string using white space
            frequencyMap.put(tokens[0], Integer.parseInt(tokens[2]));
        }
        freqReader.close();

        // Since frequency map is all in upper cases, and we don't worry about punctuation, change the phrase to all uppercases, and delete the punctuation
        String input = "Hello World".toUpperCase().replaceAll("\\s+", ""); // => "HELLOWORLD";
        HuffmanNode root = HuffmanTree.buildHuffmanTree(frequencyMap);
        HuffmanEncoderDecoder encoderDecoder = new HuffmanEncoderDecoder(root); // Inititalize HuffmanEncoderDecoder

        // Encoder, and do the junit test
        String encoded = encoderDecoder.encode(input);
        String expectedEncoded = "1101101000010001111100011111101000000101100";
        assertEquals(expectedEncoded, encoded);
        System.out.println(encoded);

        // Decoder and do the junit test
        String decoded = encoderDecoder.decode("1101101000010001111100011111101000000101100");
        String expectedDecoded = "HELLOWORLD";
        assertEquals(expectedDecoded, decoded);
        System.out.println(decoded);


    }
}
