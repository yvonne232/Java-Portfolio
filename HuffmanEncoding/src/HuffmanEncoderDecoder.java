/**
 * Name: Ziyu(Yvonne) Lin
 * Lab 3
 * EN.605.202
 */
import java.util.*;

/**
 * This is a class for encoder and decoder
 */
public class HuffmanEncoderDecoder {
    private HuffmanNode root;
    private Map<String, String> encodingMap;

    /**
     * Initialize the HuffmanEncoderDecoder with huffman tree root and encoding map
     * @param root huffman tree root
     */
    public HuffmanEncoderDecoder(HuffmanNode root) {
        this.root = root;
        this.encodingMap = HuffmanTree.buildEncodingMap(root);
    }

    /**
     * This is an encoder
     * @param input strings for encoding
     * @return encoded code
     */
    public String encode(String input) {
        StringBuilder encoded = new StringBuilder();
        for (char c : input.toCharArray()) {
            String s = String.valueOf(c);
            // Check if the encoding map has the key
            if (encodingMap.containsKey(s)) {
                encoded.append(encodingMap.get(s));
            }
        }
        return encoded.toString();
    }

    /**
     * This is a decoder
     * @param input binary bits input
     * @return decoded information
     */
    public String decode(String input) {
        StringBuilder decoded = new StringBuilder();
        HuffmanNode current = root; // the current node is the root node

        for (char c: input.toCharArray()) {
            if (c == '0') {
                current = current.left; // go to left subtree
            } else if (c == '1') {
                current = current.right; // go to right subtree
            }

            // If it is a leaf node, reset the current node to root again, and construct the decoded string
            if (current.isLeaf()) {
                decoded.append(current.chars);
                current = root;
            }
        }

        return decoded.toString();
    }
}
