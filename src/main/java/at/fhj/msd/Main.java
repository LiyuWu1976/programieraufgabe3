package at.fhj.msd;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Main {
    // Define dataset sizes and number of searches for benchmarking
    static final int[] SIZES = { 100, 1000, 10000, 100000 };
    static final int[] TIMES = { 100, 1000, 10000, 100000 };
    static final Random rand = new Random();

    public static void main(String[] args) throws IOException {
        FileWriter csv = new FileWriter("benchmark.csv");
        csv.write("Size,Method,Min(ns),Max(ns),Avg(ns)\n");
        // Loop through each test size
        for (int i = 0; i < SIZES.length; i++) {
            System.out.println("Testing size " + SIZES[i] + " with " + TIMES[i] + " searches...");
            // Generate random search targets within array range
            int[] array = generatePermutation(SIZES[i]);
            // Shuffle the array for linear search (unsorted data)
            Integer[] boxedArray = Arrays.stream(array).boxed().toArray(Integer[]::new);
            Collections.shuffle(Arrays.asList(boxedArray));
            int[] randomArray = Arrays.stream(boxedArray).mapToInt(Integer::intValue).toArray();

            // generate random targets in the same range as array values
            int[] targets = new int[TIMES[i]];
            int j = 0;
            while (j < TIMES[i]) {
                targets[j++] = rand.nextInt(SIZES[i]) + 1;
            }

            testMethod("LinearSearch", randomArray, targets, csv, SearchAlgorithms::linearSearch);
            testMethod("BinarySearch", array, targets, csv, SearchAlgorithms::binarySearch);
            testMethod("InterpolationSearch", array, targets, csv, SearchAlgorithms::interpolationSearch);
            testMethod("QuadraticBinarySearch", array, targets, csv, SearchAlgorithms::quadraticBinarySearch);
        }

        csv.close();
        System.out.println("Benchmark finished. Results saved in benchmark.csv");
    }

    // Functional interface for passing search method references
    @FunctionalInterface
    interface SearchFunction {
        int search(int[] arr, int target);
    }

    // Benchmark a given search method and write results to CSV
    static void testMethod(String name, int[] array, int[] targets, FileWriter csv, SearchFunction function)
            throws IOException {

        long min = Long.MAX_VALUE, max = 0, total = 0;

        for (int x : targets) {

            long start = System.nanoTime();
            function.search(array, x);
            long time = System.nanoTime() - start;
            // Update min, max, and total time
            min = Math.min(min, time);
            max = Math.max(max, time);
            total += time;
        }

        long avg = total / targets.length;
        // Write benchmark result to CSV
        csv.write(array.length + "," + name + "," + min + "," + max + "," + avg + "\n");
    }

    // Generate a sorted array of integers from 1 to size
    static int[] generatePermutation(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }
}
