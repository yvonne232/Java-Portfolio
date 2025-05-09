/**
 * Name: Ziyu(Yvonne) Lin
 * Lab 3
 * EN.605.202
 */
import java.util.*;

 /**
 * This class is to build Huffman Tree
 */
public class HuffmanTree {

     /**
      * This is a method to build huffman tree
      * @param frequencyMap - frequency map for building the tree; eg: A-5, B-13
      * @return - the root node of the huffman tree
      */
    public static HuffmanNode buildHuffmanTree(Map<String, Integer> frequencyMap) {

        // Use priority queue to get the two lowest-frequency nodes
        // Then combine the two lowest-frequency nodes until only one node remains
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) { // Key is character, value is frequency
            HuffmanNode node = new HuffmanNode(entry.getKey(), entry.getValue());
            pq.add(node); // Add all nodes to priority queue
        }

        while (pq.size() > 1) {
            // Retrieves and removes the head
            HuffmanNode w1 = pq.poll();
            HuffmanNode w2 = pq.poll();

            // Combine the two nodes and create a parent node
            HuffmanNode mergedNode = new HuffmanNode(w1.chars + w2.chars, w1.freq + w2.freq);
            // Link the left node and the right node
            mergedNode.left = w1;
            mergedNode.right = w2;

            // Add the mergedNode to the queue
            pq.add(mergedNode);
        }

        // return the root node
        return pq.poll();
    }

     /**
      * Print out the huffman tree by doing a preorder traversal
      * @param node huffmantree root node
      */
    public static void printHuffmanTree(HuffmanNode node) {
        // Visit the root first and then traverse left subtree, traverse right subtree
        // Base case: if node is null, return the function
        if (node == null) {
            return;
        }
        System.out.println("Node: " + node.chars + " | freq: " + node.freq); // print root node
        printHuffmanTree(node.left); // traver left subtree
        printHuffmanTree(node.right); // traverse right subtree
    }

     /**
      * Use the huffman tree to build the encoding code recursively
      * @param node one Huffman node
      * @param encodingCode encoding code for the characters
      * @param map - saved the character and the encoding code into a hashmap
      */
    private static void buildEncodingCode(HuffmanNode node, String encodingCode, Map<String, String> map) {
        // Base case: if the node is already a leaf node, put it and its corresponding code into the encoding hashmap and then return the function
        if (node.isLeaf()) {
            map.put(node.chars, encodingCode);
            return;
        }

        // build the encoding code for the left subtree
        buildEncodingCode(node.left, encodingCode+"0", map); // the left tree represents binary 0
        // build the encoding code for the right subtree
        buildEncodingCode(node.right, encodingCode+"1", map); // the right tree represents binary 1
    }

     /**
      * The function is to initialize an empty map and start the tree traversal using the recursion function
      * @param root huffman tree root node
      * @return map - encoding map
      */
    public static Map<String, String> buildEncodingMap(HuffmanNode root) {
        // Initialize an empty Map - (chars, corresponding encoding code)
        Map<String, String> map = new HashMap<>();
        // start tree traversal recursively
        buildEncodingCode(root, "", map);
        return map;
    }

     /**
      * This function is to print the encoding map
      * @param root the huffman tree root node
      */
     public static void printEncodingMap(HuffmanNode root) {
         // build the encoding map
         Map<String, String> encodingMap = buildEncodingMap(root);
         for (Map.Entry<String, String> entry : encodingMap.entrySet()) {
             System.out.println(entry.getKey() + " - " + entry.getValue());
         }
     }
 }
