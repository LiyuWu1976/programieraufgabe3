package at.fhj.msd;

/**
 * Provides implementations of different search algorithms for sorted integer arrays.
 */
public class SearchAlgorithms {

    /**
     * Linear search: scans from left to right.
     */
    public static int linearSearch(int[] a, int x) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == x) return i;
        }
        return -1;
    }

    /**
     * Binary search: divide-and-conquer in sorted array.
     */
    public static int binarySearch(int[] a, int x) {
        int low = 0, high = a.length - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1; // safe midpoint
            if (a[mid] == x) return mid;
            else if (a[mid] < x) low = mid + 1;
            else high = mid - 1;
        }

        return -1;
    }

    /**
     * Interpolation search: works well on uniformly distributed data.
     */
    public static int interpolationSearch(int[] a, int x) {
        int low = 0, high = a.length - 1;
        int maxIterations = 32 + (int)(Math.log(a.length + 1) / Math.log(2)); 

        if (a.length == 0) return -1;
        if (a[low] == x) return low;
        if (a[high] == x) return high;

        int iterations = 0;
        while (low <= high && x >= a[low] && x <= a[high]) {
            if (a[low] == a[high]) break; // prevent division by zero

        long num = (long)(x - a[low]) * (high - low);
        long den = a[high] - a[low];
        int pos = low + (int)(num / den);

        if (pos < low || pos > high) break;

        if (a[pos] == x) return pos;
        else if (a[pos] < x) low = pos + 1;
        else high = pos - 1;

        iterations++;
        if (iterations > maxIterations) break; // 超过最大次数，防止卡死
        }
    // 如果没找到，最后用二分查找兜底
        return binarySearch(a, x);
    }   
  

    /**
     * Quadratic binary search: recursively search in quarters instead of halves.
     */
    public static int quadraticBinarySearch(int[] a, int x) {
        return qbsHelper(a, x, 0, a.length - 1);
    }

    private static int qbsHelper(int[] a, int x, int low, int high) {
        if (low > high) return -1;

        int first = low + (high - low) / 4;
        int second = low + (high - low) / 2;
        int third = low + 3 * (high - low) / 4;

        if (a[first] == x) return first;
        if (a[second] == x) return second;
        if (a[third] == x) return third;

        if (x < a[first]) return qbsHelper(a, x, low, first - 1);
        else if (x < a[second]) return qbsHelper(a, x, first + 1, second - 1);
        else if (x < a[third]) return qbsHelper(a, x, second + 1, third - 1);
        else return qbsHelper(a, x, third + 1, high);
    }
}
