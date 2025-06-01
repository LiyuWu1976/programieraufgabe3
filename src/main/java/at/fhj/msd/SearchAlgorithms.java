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
     * Diese Methode sucht das Element x im sortierten Array a mithilfe der Interpolationssuche.
     */
    public static int interpolationSearch(int[] a, int x) {
        // Initialisierung, low und high geben den aktuellen Suchbereich an.
        int low = 0, high = a.length - 1; 

        /**maxIterations ist die maximale Anzahl von Wiederholungen, um endlose Schleifen zu vermeiden.
         Sie basiert auf der Logarithmusfunktion log₂(n) + ein Sicherheitsaufschlag.)
        */
        int maxIterations = 32 + (int)(Math.log(a.length + 1) / Math.log(2)); 

        /** Sonderfälle behandeln
         * Falls das Array leer ist oder das gesuchte Element direkt am Anfang oder Ende liegt → sofort zurückgeben.
        */
        if (a.length == 0) return -1;
        if (a[low] == x) return low;
        if (a[high] == x) return high;


        int iterations = 0;

        /**Hauptschleife: Interpolationssuche
         * Schleife läuft nur, wenn sich x im aktuellen Bereich befindet: a[low] bis a[high].
        */
        while (low <= high && x >= a[low] && x <= a[high]) {
            if (a[low] == a[high]) break; // prevent division by zero

        /**Interpolationsformel
         * Berechnet die geschätzte Position pos von x basierend auf linearer Skalierung.
         * long verhindert Überlauf.
        */
        long num = (long)(x - a[low]) * (high - low);
        long den = a[high] - a[low];
        int pos = low + (int)(num / den);

        /**Grenzwerte prüfen
         * Falls pos außerhalb des gültigen Bereichs liegt → abbrechen.
        */
        if (pos < low || pos > high) break;

        /**Vergleiche
        * Klassisches Vorgehen wie bei der binären Suche: Bereich nach dem Vergleich eingrenzen.
        */
        if (a[pos] == x) return pos;
        else if (a[pos] < x) low = pos + 1;
        else high = pos - 1;

        //Schutz gegen endlose Schleifen oder ineffiziente Suche bei schlecht verteilten Daten.
        iterations++;
        if (iterations > maxIterations) break; 
        }

        /**Wenn die Interpolationssuche fehlschlägt, wird eine klassische binäre Suche durchgeführt.
        Das erhöht die Robustheit der Methode.*/
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
            // Interpolationsmethode zur Schätzung von t
            int t = from + (int)Math.floor((to - from) * (double)(x - a[from]) / (a[to] - a[from]));
            // Grenzschutz
            if (t < from) t = from;
            if (t > to) t = to;

            if (a[t] == x) return t;
            int step = (int)Math.floor(Math.sqrt(n));
            if (step < 1) step = 1;

            if (x < a[t]) {
                // Nach links springen
                while (t > from && x < a[t]) t -= step;
                int left = Math.max(from, t);
                int right = Math.min(to, t + step - 1);
                return qbsHelper(a, x, left, right);
            } else {
                // Nach rechts springen
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
