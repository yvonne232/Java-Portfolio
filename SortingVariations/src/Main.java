import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * This is the main runner class
 */
public class Main {

    /**
     * This is the main runner
     * @param args args[0] - input folder; args[1] - output folder
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Usage: java MainRunner <input-folder> <output-folder>");
            return;
        }

        // Generate more input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Would you like to generate another set of input files? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();

        if (response.equals("yes") || response.equals("y")) {
            generateInputFiles();
            System.out.println("Input files generated.");
        } else {
            System.out.println("Skipping input generation.");
        }

        // get the input folder from args
        File inputFolder = new File(args[0]);
        System.out.println("Input folder: " + inputFolder.getAbsolutePath());

        File outputFolder = new File(args[1]);
        if (!outputFolder.exists()) outputFolder.mkdir();

        File[] files = inputFolder.listFiles((dir, name) -> name.endsWith(".dat"));
        if (files == null || files.length == 0) {
            System.out.println("No .dat files found.");
        }

        if (files != null) {
            System.out.println("Found " + files.length + " files");
        }

        for (File file : files) {
            String fileName = file.getName();
            int[] original = readDataFile(file);

            // QuickSort A
            runAndSave("QuickSortA", original, fileName, outputFolder);
            // QuickSort B
            runAndSave("QuickSortB", original, fileName, outputFolder);
            // QuickSort C
            runAndSave("QuickSortC", original, fileName, outputFolder);
            // QuickSort D
            runAndSave("QuickSortD", original, fileName, outputFolder);
            // Merge Sort
            runAndSave("MergeSort", original, fileName, outputFolder);

        }

        System.out.println("All sorting completed. Results saved to: " + outputFolder.getAbsolutePath());

    }

    /**
     * Read the .dat file and map it to array
     * @param file
     * @return array of input numbers
     * @throws IOException
     */
    private static int[] readDataFile(File file) throws IOException {
        List<Integer> numbers = new ArrayList<>();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextInt()) {
            numbers.add(scanner.nextInt());
        }
        scanner.close();
        return numbers.stream().mapToInt(i -> i).toArray();
    }

    /**
     * Save the sorting result
     * @param fileName input file name
     * @param sortName sort type would like to use
     * @param original array, original array
     * @param sorted sorted array
     * @param stats sort stats
     * @param durationNano execuation time
     * @param outputFolder output saved place
     * @throws IOException
     */
    private static void saveResult(String fileName, String sortName, int[] original, int[] sorted, Object stats, long durationNano, File outputFolder) throws IOException {
        String outputFileName = fileName.replace(".dat", "_" + sortName + ".dat");
        File outputFile = new File(outputFolder, outputFileName);
        PrintWriter out = new PrintWriter(outputFile);
        out.println("Original: " + Arrays.toString(original));
        out.println();
        out.println("Sorted: " + Arrays.toString(sorted));
        out.println();
        out.println("Sort: " + sortName);
        out.println("File: " + fileName);
        out.println(stats.toString());
        out.println("Execution Time: " + durationNano + " ns");
        out.close();

        // Save all the stats in one summary file
        File summaryFile = new File(outputFolder, "summary.dat");
        PrintWriter summaryWriter = new PrintWriter(new FileWriter(summaryFile, true)); // true = append mode
        summaryWriter.println("Sort: " + sortName);
        summaryWriter.println("File: " + fileName);
        summaryWriter.println(stats.toString());
        summaryWriter.println("Execution Time: " + durationNano + " ns");
        summaryWriter.println("----------------------------------------");
        summaryWriter.close();

        // Verify correctness using built-in sort
        int[] javaSorted = original.clone();
        Arrays.sort(javaSorted);
        if (!Arrays.equals(javaSorted, sorted)) {
            System.out.println("WARNING: Sorted array does not match Java built-in sort for " + fileName);
            System.out.println(" → Sort: " + sortName);
            System.out.println(" → File: " + fileName);
        }
    }

    /**
     * Run the sorting algorithm and save the results
     * @throws IOException
     */
    private static void runAndSave(String sortName, int[] original, String fileName, File outputFolder) throws IOException {
        long startTime, endTime, durationNano; // Time the sort

        int[] arr = original.clone();

        if (sortName.equals("MergeSort")) {
            // Handle linked list-based merge sort
            NaturalMergeSort.SortStats stats = new NaturalMergeSort.SortStats();
            NaturalMergeSort.Node head = NaturalMergeSort.fromArray(arr);
            startTime = System.nanoTime();
            head = NaturalMergeSort.sort(head, stats);
            int[] sorted = NaturalMergeSort.toArray(head);
            endTime = System.nanoTime();
            durationNano = endTime - startTime;
            saveResult(fileName, sortName, original, sorted, stats, durationNano, outputFolder);
            return;
        }


        QuickSort.SortStats stats = new QuickSort.SortStats();
        startTime = System.nanoTime();
        switch (sortName) {
            case "QuickSortA" -> QuickSort.quickSortA(arr, 0, arr.length - 1, stats);
            case "QuickSortB" -> QuickSort.quickSortB(arr, 0, arr.length - 1, stats);
            case "QuickSortC" -> QuickSort.quickSortC(arr, 0, arr.length - 1, stats);
            case "QuickSortD" -> QuickSort.quickSortD(arr, 0, arr.length - 1, stats);
            default -> throw new IllegalArgumentException("Unknown sort name: " + sortName);
        }
        endTime = System.nanoTime();
        durationNano = endTime - startTime;
        saveResult(fileName, sortName, original, arr, stats, durationNano, outputFolder);
    }

    /**
     * Generate more inputs function: Ascending, Reverse, and random, and with duplicates
     * @throws IOException
     */
    private static void generateInputFiles() throws IOException {
        int[] sizes = {50, 1000, 2000, 10000};
        File generated_input_folder = new File("Input/Generated_inputs");

        // If the folder exists, delete everything inside and recreate it
        if (generated_input_folder.exists()) {
            deleteFolder(generated_input_folder);  // delete everything inside
        }
        generated_input_folder.mkdir(); // recreate the folder


        for (int size: sizes) {
            // 1. Ascending
            int[] ascending = new int[size];
            for (int i = 0; i < size; i++) {
                ascending[i] = i + 1;
            }
            writeArrayToFile(ascending, generated_input_folder + "/asc" + size + ".dat");

            // 2. Reverse
            int[] reverse = new int[size];
            for (int i = 0; i < size; i++) {
                reverse[i] = size - i;
            }
            writeArrayToFile(reverse, generated_input_folder + "/rev" + size + ".dat");

            // 3. Random
            int[] random;
            random = ascending.clone();
            shuffleArray(random);
            writeArrayToFile(random, generated_input_folder + "/ran" + size + ".dat");

            // 4. Generate random with 20% duplicates (or use other percentages)
            int[] dup = generateRandomWithDuplicates(size, 0.2);
            writeArrayToFile(dup, generated_input_folder + "/dup" + size + ".dat");
        }
    }

    /**
     * Write the new array to the file
     * @throws IOException
     */
    private static void writeArrayToFile(int[] arr, String filename) throws IOException {
        PrintWriter out = new PrintWriter(filename);
        for (int val: arr) {
            out.print(val + " ");
        }
        out.close();
    }

    /**
     * Shuffle array method
     * @param arr input array
     */
    private static void shuffleArray(int[] arr) {
        Random rand = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1); // Pick a random index between 0 and i (inclusive)
            int tmp = arr[j];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

    /**
     * Generate random array with duplicates
     * @param size size of the array
     * @param duplicateRate duplicate rate
     * @return
     */
    private static int[] generateRandomWithDuplicates(int size, double duplicateRate) {
        Random rand = new Random();
        int[] arr = new int[size];
        int uniqueSize = (int)(size * (1 - duplicateRate));
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(uniqueSize);
        }
        return arr;
    }

    /**
     * Delete one folder
     * @throws IOException
     */
    private static void deleteFolder(File folder) throws IOException {
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File f : files) {
                    deleteFolder(f); // recursively delete subfolders
                }
            }
        }
        if (!folder.delete()) {
            throw new IOException("Failed to delete " + folder);
        }
    }
}
