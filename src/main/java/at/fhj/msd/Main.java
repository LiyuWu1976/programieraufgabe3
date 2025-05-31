package at.fhj.msd;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Main {

    static final int[] SIZES = {100, 1000, 10000, 100000};
    static final int[] TIMES = {100, 1000, 5000, 10000};
    static final Random rand = new Random();
    

    public static void main(String[] args) throws IOException {
        FileWriter csv = new FileWriter("benchmark.csv");
        csv.write("Size,Method,Min(ns),Max(ns),Avg(ns)\n");

        for (int i = 0; i < SIZES.length; i++) {
            System.out.println("Testing size " + SIZES[i] + " with " + TIMES[i] + " searches...");

        int[] array = generatePermutation(SIZES[i]);
        Integer[] boxedArray = Arrays.stream(array).boxed().toArray(Integer[]::new);
        Collections.shuffle(Arrays.asList(boxedArray));
        int[] randomArray = Arrays.stream(boxedArray).mapToInt(Integer::intValue).toArray();

            // generate random targets in the same range as array values
            int[] targets = new int[TIMES[i]];
            for (int j = 0; j < TIMES[i]; j++) {
                targets[j] = rand.nextInt(SIZES[i]) + 1;
            }
            

            testMethod("LinearSearch", randomArray, targets, csv, SearchAlgorithms::linearSearch);
            testMethod("BinarySearch", array, targets, csv, SearchAlgorithms::binarySearch);
            testMethod("InterpolationSearch", array, targets, csv, SearchAlgorithms::interpolationSearch);
            testMethod("QuadraticBinarySearch", array, targets, csv, SearchAlgorithms::quadraticBinarySearch);
        }

        csv.close();
        System.out.println("Benchmark finished. Results saved in benchmark.csv");
    }

    interface SearchFunction {
        int search(int[] arr, int target);
    }

    static void testMethod(String name, int[] array, int[] targets, FileWriter csv, SearchFunction function) throws IOException {
 
        long min = Long.MAX_VALUE, max = 0, total = 0;

        for (int x : targets) {
            
            long start = System.nanoTime();         
            function.search(array, x);
            long time = System.nanoTime() - start; 
            
            min = Math.min(min, time);
            max = Math.max(max, time);
            total += time;
        }

        long avg = total / targets.length;
        csv.write(array.length + "," + name + "," + min + "," + max + "," + avg + "\n");
    }


    static int[] generatePermutation(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }

}
