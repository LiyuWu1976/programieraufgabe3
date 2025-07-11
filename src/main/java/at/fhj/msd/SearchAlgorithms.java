package at.fhj.msd;

/**
 * Provides implementations of different search algorithms for sorted integer arrays.
 */
public class SearchAlgorithms {

    /**
     * Linear search: scans from left to right.
     */
    public static int linearSearch(int[] a, int x) {
   int index = 0;
   for (int value : a) {
    if (value == x) {
        return index; // match found
    }
    index++;
}

    return -1; // not found
}

    /**
     * Binary search: divide-and-conquer in sorted array.
     */
        public static int binarySearch(int[] a, int x) {
    int low = 0;
    int high = a.length - 1;

    while (low <= high) {
        int mid = (low + high) >>> 1; // calculate mid safely

        if (a[mid] == x) {
            return mid; // exact match
        } else if (a[mid] < x) {
            low = mid + 1; // search in right half
        } else {
            high = mid - 1; // search in left half
        }
    }

        return -1;
    }


    /**
     * Interpolation search: works well on uniformly distributed data.
     * Diese Methode sucht das Element x im sortierten Array a mithilfe der Interpolationssuche.
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
        
       long den = a[high] - a[low];
       long num = (long)(x - a[low]) * (high - low);
       int pos = low + (int)(num / den);

        
        if (pos < low || pos > high) break;

        if (a[pos] == x) return pos;
        else if (a[pos] < x) low = pos + 1;
        else high = pos - 1;

        //Protection against infinite loops or inefficient search in poorly distributed data.
        iterations++;
        if (iterations > maxIterations) break; 
        }

        return binarySearch(a, x);
    }   

  

    /**
     * Quadratic binary search: recursively search in quarters instead of halves.
     */
    public static int quadraticBinarySearch(int[] a, int x) {
        return qbsHelper(a, x, 0, a.length - 1);
    }

    private static int qbsHelper(int[] a, int x, int from, int to) {
        int n = to - from + 1;
        if (n <= 0) return -1;

        if (a[from] < a[to]) {
            // Interpolation method for estimating t.
            int t = from + (int)Math.floor((to - from) * (double)(x - a[from]) / (a[to] - a[from]));
            // limit safeguard
            if (t < from) t = from;
            if (t > to) t = to;

            if (a[t] == x) return t;
            int step = (int)Math.floor(Math.sqrt(n));
            if (step < 1) step = 1;

            if (x < a[t]) {
                // Jump to the left.
                while (t > from && x < a[t]) t -= step;
                int left = Math.max(from, t);
                int right = Math.min(to, t + step - 1);
                return qbsHelper(a, x, left, right);
            } else {
                // Jump to the right.
                while (t < to && x > a[t]) t += step;
                int left = Math.max(from, t - step + 1);
                int right = Math.min(to, t);
                return qbsHelper(a, x, left, right);
            }
        } else {
            if (a[from] == x) return from;
            else return -1;
        }
    }
}
