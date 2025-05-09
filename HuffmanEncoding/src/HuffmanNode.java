/**
 * Name: Ziyu(Yvonne) Lin
 * Lab 3
 * EN.605.202
 */

/**
 * This is a class for every HuffmanNode
 */
public class HuffmanNode implements Comparable<HuffmanNode> {

    // A huffman node has chars, frequency
    String chars;
    int freq;
    HuffmanNode left, right;

    /**
     * This is a constructor
     * @param chars character
     * @param freq frequency
     */
    public HuffmanNode(String chars, int freq) {
        this.chars = chars;
        this.freq = freq;
    }

    /**
     * Check if this is a leaf node
     * @return true if it is a leaf node
     */
    public boolean isLeaf() {
        return left == null && right == null;
    }

    /**
     * This is a comparator for comparing one huffman node with another huffman node, and determine the priority
     * @param o the object to be compared.
     * @return -1, 0 or 1 as this object is less than, equal to, or greater than the o object.
     */
    @Override
    public int compareTo(HuffmanNode o) {
        // Lower frequency comes first; it will return -1 if this is less than the object, 1 if this is larger than the object
        if (this.freq != o.freq) {
            return Integer.compare(this.freq, o.freq);
        }

        // If tied, single-letter comes before multi-letter; it will return -1 if this is less than the object, 1 if this is larger than the object
        if (this.chars.length() != o.chars.length()) {
            return Integer.compare(this.chars.length(), o.chars.length());
        }

        // compare alphabetically; it will return -1 if this is less than the object, 1 if this is larger than the object
        return this.chars.compareTo(o.chars);
    }

}